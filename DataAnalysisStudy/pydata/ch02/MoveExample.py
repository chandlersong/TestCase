import pandas as pd

unames = ['user_id','gender','age','occupation','zip']
users = pd.read_table('movielens//users.dat',sep='::', header=None,names=unames)

rnames = ['user_id','move_id','rating','timestamp']
ratings = pd.read_table('movielens//ratings.dat',sep='::', header=None,names=rnames)

mnames = ['move_id','title','genres']
movies = pd.read_table('movielens//movies.dat',sep='::',header=None,names=mnames)

print(users[:5])

data = pd.merge(pd.merge(ratings,users),movies)
# print(data)

dir(data)
mean_ratings = data.pivot_table('rating',index='title', columns='gender',aggfunc='mean')
print(mean_ratings[0:5])

ratings_by_title = data.groupby('title').size()
print(ratings_by_title[0:5])

active_titles=ratings_by_title.index[ratings_by_title >= 250]
print(active_titles)