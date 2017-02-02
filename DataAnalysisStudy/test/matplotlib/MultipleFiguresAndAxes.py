import unittest
from unittest import TestCase

import matplotlib.pyplot as plt
import numpy as np


class HelloWorldTest(TestCase):
    def test_first_example(self):
        def f(t):
            return np.exp(-t) * np.cos(2 * np.pi * t)

        t1 = np.arange(0.0, 5.0, 0.1)
        t2 = np.arange(0.0, 5.0, 0.02)

        plt.figure(1)
        plt.subplot(211)
        plt.plot(t1, f(t1), 'bo', t2, f(t2), 'k')

        plt.subplot(212)
        plt.plot(t2, np.cos(2 * np.pi * t2), 'r--')
        plt.show()

    def test_two_figure(self):
        plt.figure(1)  # the first figure
        plt.subplot(211)  # the first subplot in the first figure
        plt.plot([1, 2, 3])
        plt.subplot(212)  # the second subplot in the first figure
        plt.plot([4, 5, 6])

        plt.figure(2)  # a second figure
        plt.plot([4, 5, 6])  # creates a subplot(111) by default

        plt.figure(1)  # figure 1 current; subplot(212) still current
        plt.subplot(211)  # make subplot(211) in figure1 current
        plt.title('Easy as 1, 2, 3')  # subplot 211 title
        plt.show()

    def test_show_test(self):
        # Fixing random state for reproducibility
        np.random.seed(19680801)

        mu, sigma = 100, 15
        x = mu + sigma * np.random.randn(10000)

        # the histogram of the data
        n, bins, patches = plt.hist(x, 50, normed=1, facecolor='g', alpha=0.75)

        plt.xlabel('Smarts')
        plt.ylabel('Probability')
        plt.title('Histogram of IQ')
        plt.text(60, .025, r'$\mu=100,\ \sigma=15$')
        plt.axis([40, 160, 0, 0.03])
        plt.grid(True)
        plt.show()

    def test_annotating_text(self):
        ax = plt.subplot(111)

        t = np.arange(0.0, 5.0, 0.01)
        s = np.cos(2 * np.pi * t)
        line, = plt.plot(t, s, lw=2)

        plt.annotate('local max', xy=(2, 1), xytext=(3, 1.5),
                     arrowprops=dict(facecolor='black', shrink=0.05),
                     )

        plt.ylim(-2, 2)
        plt.show()


class ArtistExample(TestCase):

    def test_axs(self):
        fig = plt.figure()
        fig.subplots_adjust(top=0.8)
        ax1 = fig.add_subplot(211)
        ax1.set_ylabel('volts')
        ax1.set_title('a sine wave')

        t = np.arange(0.0, 1.0, 0.01)
        s = np.sin(2 * np.pi * t)
        line, = ax1.plot(t, s, color='blue', lw=2)

        # Fixing random state for reproducibility
        np.random.seed(19680801)

        ax2 = fig.add_axes([0.15, 0.1, 0.7, 0.3])
        n, bins, patches = ax2.hist(np.random.randn(1000), 50,
                                    facecolor='yellow', edgecolor='yellow')
        ax2.set_xlabel('time (s)')

        axis = ax1.xaxis
        print(axis.get_ticklocs())

        plt.show()

    def test_date_example(self):

        import datetime
        import numpy as np
        import matplotlib.pyplot as plt
        import matplotlib.dates as mdates
        import matplotlib.cbook as cbook

        years = mdates.YearLocator()  # every year
        months = mdates.MonthLocator()  # every month
        yearsFmt = mdates.DateFormatter('%Y')

        # load a numpy record array from yahoo csv data with fields date,
        # open, close, volume, adj_close from the mpl-data/example directory.
        # The record array stores python datetime.date as an object array in
        # the date column
        datafile = cbook.get_sample_data('goog.npy')
        try:
            # Python3 cannot load python2 .npy files with datetime(object) arrays
            # unless the encoding is set to bytes. However this option was
            # not added until numpy 1.10 so this example will only work with
            # python 2 or with numpy 1.10 and later.
            r = np.load(datafile, encoding='bytes').view(np.recarray)
        except TypeError:
            r = np.load(datafile).view(np.recarray)

        fig, ax = plt.subplots()
        ax.plot(r.date, r.adj_close)

        # format the ticks
        ax.xaxis.set_major_locator(years)
        ax.xaxis.set_major_formatter(yearsFmt)
        ax.xaxis.set_minor_locator(months)

        datemin = datetime.date(r.date.min().year, 1, 1)
        datemax = datetime.date(r.date.max().year + 1, 1, 1)
        ax.set_xlim(datemin, datemax)

        # format the coords message box
        def price(x):
            return '$%1.2f' % x

        ax.format_xdata = mdates.DateFormatter('%Y-%m-%d')
        ax.format_ydata = price
        ax.grid(True)

        # rotates and right aligns the x labels, and moves the bottom of the
        # axes up to make room for them
        fig.autofmt_xdate()

        plt.show()


if __name__ == '__main__':
    unittest.main()
