import unittest
from unittest import TestCase


class Entry:
    def __eq__(self, o: object) -> bool:
        if isinstance(o, Entry):
            return self.__name == o.__name
        return False

    def __init__(self, name: str) -> None:
        self.__name = name


class SubEntry(Entry):
    pass


class OtherEntry:
    def __init__(self, name: str) -> None:
        self.__name = name


class EqualTest(TestCase):

    def test_equal(self):
        a = Entry("same")
        b = Entry("same")
        c = Entry("diff")
        d = SubEntry("same")
        e = OtherEntry("same")

        print("a == b:{}".format((a==b)))
        print("a == c:{}".format((a==c)))
        print("b == c:{}".format((b==c)))
        print("d == a:{}".format((d == a)))
        print("e == a:{}".format((e == a)))
        print("e == d:{}".format((e == d)))

        print("a != b:{}".format((a != b)))
        print("a != c:{}".format((a != c)))
        print("b != c:{}".format((b != c)))
        print("e != a:{}".format((e != a)))
        print("e != d:{}".format((e != d)))

    def test_list(self):

        array = [Entry("1"), Entry("2"), Entry("3")]
        a = Entry("1")
        b = Entry("b")
        e = SubEntry("1")

        print("a in array:{} ".format((a in array)))
        print("b in array:{} ".format((b in array)))
        print("e in array:{} ".format((e in array)))


if __name__ == '__main__':
    unittest.main()
