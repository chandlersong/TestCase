import configparser
import unittest
from unittest import TestCase

from sqlalchemy import Column
from sqlalchemy import Integer
from sqlalchemy import String
from sqlalchemy import create_engine
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.ext.declarative import synonym_for
from sqlalchemy.ext.hybrid import hybrid_property
from sqlalchemy.orm import sessionmaker, synonym

path = "config.properties"
Base = declarative_base()


class TestExpressions(TestCase):
    def setUp(self):
        self.config = configparser.RawConfigParser()
        self.config.read(path)
        self.engine = create_engine(self.config.get('DataBase', 'db_link'), echo=True)
        Base.metadata.create_all(self.engine)
        self.session = sessionmaker(bind=self.engine)()

    def test_hello_world(self):
        ed_user = ExpressionUser(firstname='ed', lastname='Ed Jones')
        self.session.add(ed_user)
        self.session.commit()


class ExpressionUser(Base):
    __tablename__ = 'expressions_user'
    id = Column(Integer, primary_key=True)
    firstname = Column(String(50))
    lastname = Column(String(50))

    fullname = Column("fullname", String)
    _fullname = synonym("_fullname", map_column=True)

    @property
    def _fullname(self):
        return "aa"

    @_fullname.setter
    def _fullname(self, fullname):
        print(fullname)






if __name__ == '__main__':
    unittest.main()
