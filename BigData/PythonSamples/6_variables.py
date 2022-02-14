
#variable example
x = 5
y = "John"
print(x)
print(y)

#casting
x = str(3)    # x will be '3'
y = int(3)    # y will be 3
z = float(3)  # z will be 3.0
print(x)
print(y)
print(z)


#get type
print(type(x))
print(type(y))
print(type(z))

#single or double quotes
name="Hencil"
#same as
name='Hencil'
print(name)

#case sensitive - below will crate two variables
age=10
Age=25

#Many values to multiple variables
print("Many values to multiple variable")
x, y, z = "Orange", "Apple", "Banana"
print ( "x = " + x + "  : y = " + y + "  :  z = " + z )

#same value to multiple variable
print("same value to multiple variable")
x=y=z ="same value"
print ( "x = " + x + "  : y = " + y + "  :  z = " + z )

#Unpack collections
print("Unpack collection")
fruits = ["Apple", "Orange", "Banana"]

a,b,c = fruits
print ( "a = " + a + "  : b = " + b + "  :  c = " + c )


#output variables example
print("Output variables example ")
print("value of a is " + a )
print ("a + b  = "  + a+b)  #same type of variables can be added.
#another one is using format function
#but string and int CAN NOT be added.


#global variable
g_var = "global initial value"
def sample_fun():
    print(g_var)
    #g_var = "global variable value"

def sample_fun2():
    global g_var2;
    g_var2 = "global variable value 2"

sample_fun()
sample_fun2()

print("global variable example")
print(g_var)
print(g_var2)