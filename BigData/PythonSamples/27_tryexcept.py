#try except
#single exception
try:
    print(x)
except:
    print("An exception occured..")

#multiple exceptions
try:
    print(x)
except NameError:
    print("Variable x is not defined...")
except:
    print("Something else went wrong.")

#else - if no errors raised, else gets executed
try:
    x=10
    print(x)
except NameError:
    print("Variable x is not defined...")
else:
    print("Nothing went wrong.")

#finally
#finally block will be executed regardless if the try block raises an error or not
try:
    x=10
    print(x)
except:
    print("Exception raised...")
finally:
    print("finally called..")

#raise an exception
x = -1
if x < 0:
    raise  Exception("Sorry, no numbers below zero")

x = "hello"

if not type(x) is int:
  raise TypeError("Only integers are allowed")