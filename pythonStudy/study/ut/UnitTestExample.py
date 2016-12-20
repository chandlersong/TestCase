import unittest
from unittest import TestCase


class TestSetupRun(TestCase):
    def setUp(self):
        print("setup has run")

    def test_case_1(self):
        print("run test case 1")

    def test_case_2(self):
        print("run test case 2")


if __name__ == '__main__':
    unittest.main()
