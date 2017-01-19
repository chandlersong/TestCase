import unittest
from unittest import TestCase

import matplotlib.pyplot as plt


class HelloWorldTest(TestCase):
    """
    http://matplotlib.org/2.0.0/users/pyplot_tutorial.html#controlling-line-properties
    example is from the website above

    change style base on
    http://matplotlib.org/2.0.0/api/_as_gen/matplotlib.axes.Axes.plot.html#matplotlib.axes.Axes.plot
    """
    def test_first_line(self):
        plt.plot([1, 2, 3, 4])
        plt.ylabel('some numbers')
        plt.show()

    def test_draw_point_view(self):
        plt.plot([1, 2, 3, 4], [1, 4, 9, 16], 'ro')
        plt.axis([0, 6, 0, 20])
        plt.show()
        plt.plot([1, 2, 3, 4], [1, 4, 9, 16], linewidth=1.0)
        plt.show()
        plt.plot([1, 2, 3, 4], [1, 4, 9, 16], linewidth=5.0)
        plt.show()

if __name__ == '__main__':
    unittest.main()
