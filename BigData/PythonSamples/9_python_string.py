# 'hello' is the same as "hello"
x='hello'
print("type(x) = {0} : x = {1}".format(type(x),x))
x="hello"
print("type(x) = {0} : x = {1}".format(type(x),x))

#multiline string - using tripple single/double quotes
x = """
    line 1
    line 2
    """
print("type(x) = {0} : x = {1}".format(type(x),x))

x = '''
    line 1
    line 2
    '''
print("type(x) = {0} : x = {1}".format(type(x),x))

#slicing  - return range of characters by using slice syntax [:]
x="Hello, World!"
print(x[1:3])
#slice from the start
print(x[:3])
#slice to end
print(x[2:])
#negative indexing  - start slicing from the end
print(x[-4:])
print(x[-4:-1])

#modify string
#e.g upper, lower, split

#string concatenation - using "+" to add two string

#format string - use .format function

#escape sequence - use "\"