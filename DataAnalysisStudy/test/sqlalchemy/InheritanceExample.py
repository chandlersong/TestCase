import configparser
import unittest
from unittest import TestCase

from sqlalchemy import Column
from sqlalchemy import Integer
from sqlalchemy import String
from sqlalchemy import create_engine
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker

path = "config.properties"
Base = declarative_base()


class TestSingleTable(TestCase):
    def setUp(self):
        self.config = configparser.RawConfigParser()
        self.config.read(path)
        self.engine = create_engine(self.config.get('DataBase', 'db_link'), echo=False)
        Base.metadata.create_all(self.engine)
        self.session = sessionmaker(bind=self.engine)()

    def test_save_manager(self):
        mananger = SingleTableManager(name="test1", manager_data="bb")
        self.session.add(mananger)
        self.session.commit()


class SingleTableEmployee(Base):
    __tablename__ = 'single_table_employee'
    id = Column(Integer, primary_key=True)
    name = Column(String(50))
    manager_data = Column(String(50))
    engineer_info = Column(String(50))
    type = Column(String(20))

    __mapper_args__ = {
        'polymorphic_on': type,
        'polymorphic_identity': 'employee'
    }


class SingleTableManager(SingleTableEmployee):
    __mapper_args__ = {
        'polymorphic_identity': 'manager'
    }


class SingleTableEngineer(SingleTableEmployee):
    __mapper_args__ = {
        'polymorphic_identity': 'engineer'
    }


if __name__ == '__main__':
    unittest.main()
