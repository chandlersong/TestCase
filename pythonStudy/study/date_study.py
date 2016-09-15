import unittest
from datetime import datetime, timedelta
from unittest import TestCase


class DateTimeTest(TestCase):
    def test_simple_example(self):
        now = datetime.now()
        time1 = datetime(2010, 1, 1)

        one_year = timedelta(days=365)
        print(time1 < now)
        while time1 < now:
            start_time = time1
            print("start time:%s" % start_time)

            time1 = start_time + one_year
            if time1 > now:
                end_time = now
            else:
                end_time = time1
            print("end time:%s" % end_time)


if __name__ == '__main__':
    unittest.main()
