#simple function
def simpleFunction():
    print("simple function called...")

simpleFunction()

#function with argument
def simpleFunctionWithArgument(argument):
    print("simpleFunctionWithArgument called. argument : {0}".format(argument))

simpleFunctionWithArgument("sanjay")

#Arbitrary Arguments
#parameter values are passed as tuples
def functionWithArbitraryArguments(*args):
    print("Printing arbitrary argument values")
    for argValue in args:
        print(argValue)

functionWithArbitraryArguments("one", "two", "three", "four", "five")

#Keyword Arguments
def functionKeywordArguments(karnatakaCapital, tamilNaduCapital, andraCapital):
    print("karnatakaCapital = {0}".format(karnatakaCapital))
    print("tamilNaduCapital = {0}".format(tamilNaduCapital))
    print("andraCapital = {0}".format(andraCapital))

#order does not matter
functionKeywordArguments(tamilNaduCapital="Chennai",andraCapital ="Hydrabad", karnatakaCapital="Bangalore" )

#Arbitrary Keyword Arguments : **kwarg
#receive as dictionary
def arbitraryKeywordArguments(**kChildren):
    print("Arbitrary keyword arguments...")
    print("argument type : {0}".format(type(kChildren)))
    print("Dictionary items count : {0}".format(len(kChildren)))
    print("Dictionary items : {0}".format(kChildren))
    print("Dictionary - print only values...")
    for key in kChildren:
        print(kChildren[key])

arbitraryKeywordArguments(child1="Sebastian", child2="Andrew", child3="Manohar")


#function with default value
def printCountryName(country='India'):
    print("Default value test. Country name : {0}".format(country))

printCountryName("Singapore")
printCountryName()

#we can pass list as argument
#use pass statement if no action in function
def dummyfunction():
    pass

#recursive function
def functionRecursion(value):
    if value > 0:
        result = value + functionRecursion(value -1 )
        print(result)
    else:
        result = 0

    return  result

functionRecursion(6)



#Lambda functions
#simple lambda functions
print("lambda functions....")
function1 = lambda x: x + 10
value = function1(2)

print ("lambda x: x + 10  ==> {0}".format(function1(15)))

function2 = lambda x, y: x + y
print ("lambda x, y: x + y  ==> {0}".format(function2(15,13)))

#importance of lambda function
#function return another function as result.

def functionMultiply(x1):
    return lambda a : a* x1

lambdaMultiply = functionMultiply(10)
print("lambda function usage : {0}".format(lambdaMultiply(4)))