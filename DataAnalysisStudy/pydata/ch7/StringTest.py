from pandas import DataFrame, Series
import pandas as pd
import numpy as np
import re

val = 'a,b, guido'
print(val.split(','))

pieces = [x.strip() for x in val.split(',')]
print(pieces)

first, second, third = pieces
print(first + '::' + second + '::' + third)
print('::'.join(pieces))

print('guido' in val)
print(val.index(','))
print(val.find(':'))
print(val.count(','))
print(val.replace(',', '::'))
print(val.replace(',', ''))

text = 'foo    bar\t baz   \tqux'
print(re.split('\s+', text))

regex = re.compile('\s+')
print(regex.split(text))
print(regex.findall(text))

text = """Dave dave@google.com
Steve steve@gamil.com
Rob rob@gmail.com
Ryan ryan@yahoo.com
"""

pattern = r'[A-Z0-9._%+=]+@[A-Z0-9.-]+\.[A-Z]{2,4}'
regex = re.compile(pattern, flags=re.IGNORECASE)
print(regex.findall(text))

m = regex.search(text)
print(m)
print(text[m.start():m.end()])
print(regex.match(text))
print(regex.sub('REDACTED', text))

pattern = r'([A-Z0-9._%+-]+)@([A-Z0-9.-]+)\.([A-Z]{2,4})'
regex = re.compile(pattern, flags=re.IGNORECASE)
m = regex.match('wesm@bright.net')
print(m.groups())
print(regex.findall(text))
print(regex.sub(r'Username:\1 Domain:\2, Suffix:\3', text))

regex = re.compile(r"""
    (?P<username>[A-Z0-9._%+-]+)
    @
    (?P<domain>[A-Z0-9.-]+)
    \.
    (?P<suffix>[A-Z]{2,4})""",
                   flags=re.IGNORECASE | re.VERBOSE
                   )

m = regex.match('wesm@bright.com')

data = {
    'Dave': 'dave@google.com',
    'Steve': 'steve@gmail.com',
    'Rob': 'rob@gmail.com',
    'Wes': np.nan
}

data = Series(data)
print(data)
print(data.isnull())
print(data.str.contains('gmail'))
print(data.str.findall(pattern, flags=re.IGNORECASE))

matches = data.str.match(pattern, flags=re.IGNORECASE)
print(matches)
print(matches.str.get(1))
print(matches.str[0])
print(data.str[:5])
