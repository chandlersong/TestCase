import unittest
from unittest import TestCase
from unittest.mock import MagicMock, Mock, patch


class Production:
    def raise_exception(self):
        raise Exception("test")

    def patch(self):
        print("patch")


@patch('pandas.DataFrame')
def test_patch(MockClass1):
    print(MockClass1)


class BasicMock(TestCase):
    def test_hello_word(self):
        thing = Production()
        thing.method = MagicMock(return_value=3)
        print(thing.method("mock", key='value'))
        thing.method.assert_called_with("mock", key='value')
        self.assertRaises(AssertionError, thing.method.assert_called_with, "not_run", key='value')

    def test_mock_error(self):
        mock = Mock(side_effect=KeyError('foo'))
        self.assertRaises(KeyError, mock)

    def test_mock_method(self):
        mock = Mock(side_effect=KeyError('foo'))
        values = {'a': 1, 'b': 2, 'c': 3}

        def side_effect(arg):
            return values[arg]

        mock.side_effect = side_effect
        print((mock('a'), mock('b'), mock('c')))

        mock.side_effect = [5, 4, 3, 2, 1]
        print((mock(), mock(), mock(), mock(), mock()))

    @patch('pandas.DataFrame')
    def test_mock_patch(self, mockDataFrame):
        with patch.object(Production, 'patch', return_value=None) as mock_method:
            thing = Production()
            print(thing.patch(1, 2, 3))
        print(mockDataFrame)
        test_patch()


if __name__ == '__main__':
    unittest.main()
