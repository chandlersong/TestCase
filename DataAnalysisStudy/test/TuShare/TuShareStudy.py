import unittest
from unittest import TestCase

import tushare as ts


class TestFundamentalDataInfo(TestCase):

    def setUp(self):
        print(ts.__version__)


    def test_stock_basic(self):
        df = ts.get_stock_basics()
        df.to_json("to_json_orient_index.json", orient="index")
        df.to_json("to_json_orient_split.json", orient="split")
        df.to_json("to_json_orient_records.json", orient="index")
        df.to_json("to_json_orient_columns.json", orient="columns")
        df.to_json("to_json_orient_values.json", orient="values")

        df.reset_index(level=0, inplace=True)
        print(df)
        df.to_json("new_index_index.json", orient="index")
        df.to_json("new_index_split.json", orient="split")
        df.to_json("new_index_records.json", orient="index")
        df.to_json("new_index_columns.json", orient="columns")
        df.to_json("new_index_values.json", orient="values")


class TestStockPrices(TestCase):
    def test_stock_prices(self):
        df = ts.get_h_data('600000', start='2015-01-01', end='2016-03-16')
        df.to_json("stock_prices_index.json", orient="index")
        df.to_json("stock_prices_split.json", orient="split")
        df.to_json("stock_prices_records.json", orient="index")
        df.to_json("stock_prices_columns.json", orient="columns")
        df.to_json("stock_prices_values.json", orient="values")


if __name__ == '__main__':
    unittest.main()
