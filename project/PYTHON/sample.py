# -*- coding:utf-8 -*-
"""
@author:cuibo
@file:sample.py
@time:2018/4/2015:00
"""
" 表达式"
a= 10;
b= 20;
print(a and b)


"数据结构"
print("------数据结构")
list1 = ['abc','lkl',1991,2002];
list2 = [1,2,3,4,5,6,7,8];
print('list1[0]: ',list1[0])
print('list1[1:5]: ',list1[1:3])

list1 = ('abc','lkl',1991,2002);
list2 = (1,2,3,4,5,6,7,8);

'字典'
dict1 = {"name":"panada","age":7}

'----------控制流 '
'--------- if elif else'
x=10
if x >10:
    print("X 大于 10")
elif x< 20 :
    print("x 小于 10")
else:
    print('zero')

'--------- while 循环'
n= 100
sum = 0
counter = 1
while counter <=n:
    sum = sum +counter
    counter +=1
print("sum:",sum)
print('1 到 %d 之和为 ： %d' %(n,sum))


'----------for 循环'
language = ["c","c++","java","python","scala"]
for x in language:
    print(x)

print("-------------")
