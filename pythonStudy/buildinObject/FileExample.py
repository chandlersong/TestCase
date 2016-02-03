f = open('data.txt', 'w',encoding='utf-8')
f.write('Hello\n')
f.write('world\n')
f.close()

f = open('data.txt')
text = f.read()
print(text)  # print interprets control character
print(text.encode('utf-8'))
