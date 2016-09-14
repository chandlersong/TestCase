from unittest import TestCase
import unittest


class DecoratorExample(TestCase):
    def test_simple_method_define(self):
        def decorator(F):
            def wrapper(*args):
                print("I am wrapper")
                for var in args:
                    print(var)

            return wrapper

        @decorator
        def func(x, y):
            print("I am func")

        class C:
            @decorator
            def method(self, x, y):
                print("I am in class")

        func(1, 2)
        C().method(7, 8)

    def test_simple_class_define(self):
        class Decorator:
            def __init__(self,C):
                self.C = C

            def __call__(self, *args, **kwargs):
                self.wrapped = self.C(*args,**kwargs)
                return self

        @Decorator
        class C:
            pass

        print(C())

if __name__ == '__main__':
    unittest.main()
