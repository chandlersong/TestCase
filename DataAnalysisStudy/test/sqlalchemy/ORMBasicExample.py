import configparser
import unittest
from unittest import TestCase

from sqlalchemy import Column, Integer, String
from sqlalchemy import create_engine
from sqlalchemy.ext.declarative import declarative_base
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

    def test_hello_world(self):
        ed_user = BasicUser(name='ed', fullname='Ed Jones', password='edspassword')
        print(ed_user)
        self.session.add(ed_user)
        self.session.commit()

    def test_update(self):
        ed_user = BasicUser(name='ed', fullname='Ed Jones', password='edspassword')
        print(ed_user)
        self.session.add(ed_user)
        print(ed_user.id)
        self.session.commit()

        ed_user.password = "newPassword"

        self.session.commit()


class BasicUser(Base):
    __tablename__ = "basic_user"

    id = Column(Integer, primary_key=True)
    name = Column(String)
    fullname = Column(String)
    password = Column(String)

    def __repr__(self):
        return "<User(name='%s', fullname='%s', password='%s')>" % (
            self.name, self.fullname, self.password)


if __name__ == '__main__':
    unittest.main()
