#list
#use square bracket to create list
lstNumbers = ["One", "Two", "Three", "Four",1]

#print list
print("print list numbers : {0}".format(lstNumbers))
print("print list using for loop:")
for item in lstNumbers:
    print(item)

#access using index
print('Access using index : ')
print (lstNumbers[1])

#modify the item
print ("modify the item :")
lstNumbers[0] = "One One"
print(lstNumbers)

#print length
print("Length of the list : {0}".format(len(lstNumbers)))
#type
print("Type of the list : {0}".format(type(lstNumbers)))
#use list() constructor - double round backet
lstCopy = list(("Apple", "Orange"))
print("type : {0}  , elements : {1}".format(type(lstCopy), lstCopy))

#slicing can be applied
print("lstNumbers[-1] : {0}".format(lstNumbers[-1]))
print("lstNumbers[-3:-1] : {0}".format(lstNumbers[-3:-1]))

#check if item exist
if "Two" in lstNumbers:
    print("Yes. Two exist in the given list")

#Change list item
lstNumbers[1] = "Two Overwrite"
print("Two has been overwritten : {0}".format(lstNumbers))
lstNumbers[2:4] = ["Three Overwrite","Four Overwrite"]
print("Three and Four have been overwritten : {0}".format(lstNumbers))
#insert items
lstNumbers.insert(1, "Two")
print("Two inserted : {0}".format(lstNumbers))

#new list
listFruits = ["apple", "orange", "mango", "cherry"]
listFlowers = ["rose", "jasmine", "lilly"]
#extend list
listFruits.extend(listFlowers)
print("extend : {0}".format(listFruits))

#Add list items

#Remove list items
print("origional list : {0}".format(listFruits))
listFruits.remove("apple") # remove the specified item
print("listFruits.remove(\"apple\") : {0}".format(listFruits))
listFruits.pop()#remove the item from the last index
print("listFruits.pop() : {0}".format(listFruits))
listFruits.pop(1)#remove the item from the 1st index
print("listFruits.pop(1) : {0}".format(listFruits))
del listFruits[0] #delete the first item
print("del listFruits[0]  : {0}".format(listFruits))
del listFruits #delete all the  items
#print("del listFruits  : {0}".format(listFruits)) - list is fully deleted.
#create new list from the existing one
listFruits = ["apple", "orange", "mango", "cherry"]
#clear list
listFruits.clear()

#Loop list
listFruits = ["apple", "orange", "mango", "cherry"]
print("loop the list")
for item in listFruits:
    print(item)

print("loop the list using index")
for index in range(len(listFruits)):
    print(listFruits[index])

print("loop the list using while loop")
index = 0
while index < len(listFruits):
    print(listFruits[index])
    index+=1

print("loop the list using list comprehension approach")
[print(item) for item in listFruits]

#List comprehension
listNew = []

for item in listFruits:
    if "a" in item:
        listNew.append(item)

print("New list WITHOUT using list comprehension : {0}".format(listNew))

#with if
listNew = [item for item in listFruits if "a" in item]
print("New list using list comprehension (with if) : {0}".format(listNew))

#without if
listNew = [item for item in listFruits]
print("New list using list comprehension (without if) : {0}".format(listNew))

#with if else (move the if condition left side of the for loop)
listNew = [item if "a" in item else "Durian" for item in listFruits ]
print("New list using list comprehension (with if else) : {0}".format(listNew))

#Sort list
#made all the list content to lower case
listNew = [item.lower() for item in listFruits]
print("Sort list example - ascending")
listNew.sort()
print(listNew)
print("Sort list example - descending")
listNew.sort(reverse=True)
print(listNew)

#sort using custom sort function
#TO DO : Correct the logic
def modify_item(item):
    newItem = "A" + item + "Z"
    return newItem

print("custom function sort..")
listFruits.sort(key=modify_item)
print(listFruits)

#copy list
#approach 1 : use copy method
listFruits2 = listFruits.copy()
print("list copy example : {0}".format(listFruits2))

#approach 2 : use list() method
listFruits2 = list(listFruits)
print("list cons example : {0}".format(listFruits2))


#Join list - join two list
#1. use + operator
#2. use append function
#3. use extend method

#List method