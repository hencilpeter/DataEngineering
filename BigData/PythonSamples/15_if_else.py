#if elif else


x = 10
y = 20
z = 30

#if condition
if x == 10:
    print("x is equal to 10.")

#else condition
if x >= 10:
    print("x is greater than or equal to 10.")
else:
    print("x is less than to 10.")

#if condition short hand
if x == 10: print("x is equal to 10.")

#if-else short hand
print("x is greater than or equal to 10") if x >= 10 else print("x less than 10")

#if-el if-else
if x > y:
    print("x is greater than 10.")
elif z > y:
    print("")
else:
    print("test")


#One line if else statement, with 3 conditions:
a = 330
b = 330
print("A") if a  == 330  else print("=") if a == b else print("B")

#nested if
if 10 > 9:
    if 6 > 3:
        if 2 > 1:
            print("1 > 2")

#pass statement
name = "kaipullai"
if name == "kaipullai":
    pass
else:
    print("given name is not kaipullai...")

#without using pass
if name != "kaipullai":
    print("given name is not kaipullai...")