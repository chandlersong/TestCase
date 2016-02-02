S="abc"
Y = S.__add__("de")
print(S)
print(Y)
print('sp\xc4m')
print(b'sp\xc4m')  # b means its byte here?

import re
match = re.match('Hello[ \t]*(.*)world', 'Hello Python world')
print(match.group(1))
match = re.match('[/:](.*)[/:](.*)[/:](.*)', '/usr/home:lumberjack')
print(match.groups())
print(re.split('[/:]', '/usr/home/lumberjack'))