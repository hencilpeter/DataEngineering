#local scope
#variable declared inside the function can't be accessed outside of it
def localFunction():
    localVariable = 200
    print(localVariable)

localFunction()
#local variable can be accessed from a function within the function
def outerLocalFunction():
    outlocalVariable = 100
    def innerLocationFunction():
        print(outlocalVariable)

    innerLocationFunction()

outerLocalFunction()

#global scope
#created outside of function can be assessed insidethe function as well.
x = 100
def myfunction():
    print(f"local function call. x : ${x}")

myfunction()
print(x)

#if we modify the same variable name, python will treat both are different variables.

y = 150
def myfunction():
    global y
    y = 200
    print(f"local function. y = {y}")

print(f"global print1 : {y}")
myfunction()
print(f"global print2 : {y}")
