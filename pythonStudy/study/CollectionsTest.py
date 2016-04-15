import collections

'''
 here just show how collections.defaultDict work,
 it will take a function object as parameter. the paramter will need to return a default value
'''
defaultDic = collections.defaultdict(lambda : 2);
print(defaultDic["default"])
