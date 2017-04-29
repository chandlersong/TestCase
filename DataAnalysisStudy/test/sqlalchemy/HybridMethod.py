import configparser
import unittest
from unittest import TestCase

from sqlalchemy import Integer, Column, create_engine, orm
from sqlalchemy.dialects.postgresql import JSONB
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.ext.hybrid import hybrid_property
from sqlalchemy.orm import sessionmaker

path = "config.properties"
Base = declarative_base()

class TestBasic(TestCase):
    def setUp(self):
        self.config = configparser.RawConfigParser()
        self.config.read(path)
        self.engine = create_engine(self.config.get('DataBase', 'db_link'), echo=True)
        Base.metadata.create_all(self.engine)
        self.session = sessionmaker(bind=self.engine)()

    def test_save(self):
        ed_user = HyBridClass(first_name="bb")
        self.session.add(ed_user)
        self.session.commit()


class HyBridClass(Base):
    __tablename__ = "hybrid_user"
    id = Column(Integer, primary_key=True)
    info = Column(JSONB,default=lambda: {})

    def __init__(self,**kwargs):
        Base.__init__(self,**kwargs)

    @orm.reconstructor
    def init_on_load(self):
      if self.info is None:
          self.info = {}

    @hybrid_property
    def first_name(self):
        return self.info['first_name']

    @first_name.setter
    def first_name(self,first_name):
        if self.info is None:
            self.info = {}
        self.info['first_name'] = first_name



if __name__ == '__main__':
    unittest.main()
