from unittest import TestCase
import unittest

class MagicExample(TestCase):
    def test_getattr(self):
        class C:
            def __getattr__(self, item):
                def wrap(*args,**kwargs):
                    print("I am wrap")
                    print(args)
                    print(kwargs.get('a'))
                print(type(item))
                return wrap

        c = C()
        c.not_exist(1,a=True)
        c.not_exist(1)