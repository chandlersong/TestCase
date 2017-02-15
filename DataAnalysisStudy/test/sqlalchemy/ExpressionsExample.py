import configparser
import unittest
from unittest import TestCase

from sqlalchemy import Column
from sqlalchemy import Integer
from sqlalchemy import String
from sqlalchemy import create_engine
from sqlalchemy import event
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker

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
        ed_user._fullname = "bb"
        self.session.commit()
        for instance in self.session.query(ExpressionUser):
            print(instance)


class ExpressionUser(Base):
    __tablename__ = 'expressions_user'
    id = Column(Integer, primary_key=True)
    firstname = Column(String(50))
    lastname = Column(String(50))

    _fullname = Column("fullname", String)

    @property
    def fullname(self):
        return "aa"

    @fullname.setter
    def fullname(self, fullname):
        print(fullname)


"""
event refer to
    http://docs.sqlalchemy.org/en/latest/orm/events.html
    and
    http://docs.sqlalchemy.org/en/latest/orm/session_events.html
"""
@event.listens_for(ExpressionUser, 'before_update')
@event.listens_for(ExpressionUser, 'before_insert')
def set_user_fullname(apper, connection, target):
    print(target.fullname)
    target._fullname = target.fullname


@event.listens_for(ExpressionUser, 'load')
def receive_load(target, context):
    target.fullname = "load called"


if __name__ == '__main__':
    unittest.main()
