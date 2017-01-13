import unittest
from unittest import TestCase

import tushare as ts


class TestFundamentalDataInfo(TestCase):
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


if __name__ == '__main__':
    unittest.main()
