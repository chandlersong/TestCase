import configparser
import unittest
from unittest import TestCase

from sqlalchemy import Column, Integer, String
from sqlalchemy import ForeignKey
from sqlalchemy import create_engine
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker, relationship

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

    def test_relationship(self):
        ed_user = RelationshipUser(name='ed', fullname='Ed Jones', password='edspassword')
        address1 = RelationshipAddress(email_address="aa")
        address2 = RelationshipAddress(email_address="bb")
        ed_user.addresses = [address1,address2]

        self.session.add(ed_user)
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


class RelationshipUser(Base):
    __tablename__ = "relationship_user"

    id = Column(Integer, primary_key=True)
    name = Column(String)
    fullname = Column(String)
    password = Column(String)

    addresses = relationship("RelationshipAddress", back_populates='user',
                cascade = "all, delete, delete-orphan")

    def __repr__(self):
        return "<User(name='%s', fullname='%s', password='%s')>" % (
            self.name, self.fullname, self.password)


class RelationshipAddress(Base):
    __tablename__ = 'relationship_addresses'

    id = Column(Integer, primary_key=True)
    email_address = Column(String, nullable=False)
    user_id = Column(Integer, ForeignKey('relationship_user.id'))

    user = relationship("RelationshipUser", back_populates="addresses")

    def __repr__(self):
        return "<Address(email_address='%s')>" % self.email_address


if __name__ == '__main__':
    unittest.main()
