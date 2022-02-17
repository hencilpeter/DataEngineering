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
print(x[:]) # print the entire string
print(x[1:3]) #print the characters from index 1 until 3-1
#slice from the start
print(x[:3])
#slice to end
print(x[2:])
#negative indexing  - start slicing from the end
print(x[-4:])
print("x[-4:-5] : {0}".format(x[-4:-1]))
#skip
print('x[:5]  = {0}'.format(x[:5:3]))


#modify string
#e.g upper, lower, split
print('\"tom\".upper()  = {0}'.format("tom".upper()))
print('\"TOM\".lower()  = {0}'.format("TOM".lower()))
print('\"TOM\".lower()  = {0}'.format("TOM".casefold()))
print('singapore is an asian country".split(" ")  = {0}'.format("singapore is an asian country".split(" ")))
print('singapore is an asian country".center()={0}{1}{2}'.format("|","singapore".center(50),"|"))
print('behind the enimies line.title() : {0}'.format("behind the enimies line".title()))


#string concatenation - using "+" to add two string

#format string - use .format function

#escape sequence - use "\"