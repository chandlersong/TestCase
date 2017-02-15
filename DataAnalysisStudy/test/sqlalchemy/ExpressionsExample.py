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
        self.engine = create_engine(self.config.get('DataBase', 'db_link'), echo=False)
        Base.metadata.create_all(self.engine)
        self.session = sessionmaker(bind=self.engine)()

    def test_hello_world(self):
        ed_user = ExpressionUser(firstname='ed', lastname='Ed Jones')
        self.session.add(ed_user)
        # trigger before_update
        self.session.commit()
        # trigger before_update
        ed_user._fullname = "bb"
        self.session.commit()
        # won't trigger update
        ed_user.another_property = "changed"
        for instance in self.session.query(ExpressionUser):
            print(instance)


class ExpressionUser(Base):
    __tablename__ = 'expressions_user'
    id = Column(Integer, primary_key=True)
    firstname = Column(String(50))
    lastname = Column(String(50))

    _fullname = Column("fullname", String)

    def __init__(self, another_property="aa", *args, **kwargs):
        self.__another_property = another_property

    @property
    def fullname(self):
        return "aa"

    @fullname.setter
    def fullname(self, fullname):
        print(fullname)

    @property
    def another_property(self):
        return "aa"

    @another_property.setter
    def another_property(self, another_property):
        print("another_property")


"""
event refer to
    http://docs.sqlalchemy.org/en/latest/orm/events.html
    and
    http://docs.sqlalchemy.org/en/latest/orm/session_events.html
"""


@event.listens_for(ExpressionUser, 'before_update')
@event.listens_for(ExpressionUser, 'before_insert')
def set_user_fullname(apper, connection, target):
    print('before_insert or before_update')
    print(target.fullname)


@event.listens_for(ExpressionUser, 'load')
def receive_load(target, context):
    target.fullname = "load called"


if __name__ == '__main__':
    unittest.main()
