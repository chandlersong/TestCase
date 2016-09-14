from unittest import TestCase
import unittest

class MagicExample(TestCase):
    def test_method(self):
        class C:
            def __getattr__(self, item):
                def wrap(*args):
                    print("I am wrap")
                    print(args)
                print(item)
                return wrap
            
        c = C()
        c.not_exist(1)