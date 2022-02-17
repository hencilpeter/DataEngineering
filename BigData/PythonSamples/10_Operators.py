#Arithmetic operators
x = 5
y = 2
print ( "x ** y = {0}".format( x ** y) )
print ( "x / y = {0}".format( x / y) )
print ( "x // y = {0}".format( x // y) )
#ceil


#Assignment operators
x = 4
y = 3
print ('4 & 3 = {0}'.format(4 & 3))

x = 5
y = 3
print ('5 & 3 = {0}'.format(5 & 3))



print ( "x ^ y = {0}".format( x ^ y) ) # exclusive OR

#logical operators
x= 5
y=10
z=15

if x<y and y < z :
    print("AND condition succeeded...")

if x <y or z<y :
    print("OR condition succeeded")

if not (x > y):
    print("NOT succeeded...")

x ='a'
y='b'
z='a'

if x is z:
    print("x and z are same")

if x is not y:
    print("x is not y")

x  = 10
x+=10 # x = x+ 10

x = 13
print("x = 13, value = {0}".format(x))
x >>=1
print("x >>=1, value = {0}".format(x))
x >>=1
print("x >>=1, value = {0}".format(x))
