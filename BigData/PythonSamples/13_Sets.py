#sets
setsFruits = {'apple', 'orange','banana'}
print("type(setsFruits) = {0} : setsFruits = {1}".format(type(setsFruits),setsFruits))

#sets CAN NOT be accessed using index. e.g setsFruits[1]

for item in setsFruits:
    print(item)

#add set items
setsFruits.add("grapes")
print("type(setsFruits) = {0} : setsFruits = {1}".format(type(setsFruits),setsFruits))

#add elements using update function
setsFruits2 = {'papaya', 'pineapple', 'mango'}
setsFruits.update(setsFruits2)
print("type(setsFruits) = {0} : setsFruits.update(setsFruits2) = {1}".format(type(setsFruits),setsFruits))

#The object in the update() method does not have to be a set, it can be any iterable object (tuples, lists, dictionaries etc.).


#remove item from the set
#use remove - if item does not exist, it will throw error
#use discard  - - if item does not exist, it WILL NOT throw error
#clear - empty the list
# The del keyword will delete the set completely:


#loop the list
for item in setsFruits:
    print(item)


#Join Sets
setFlower1 = {'rose', 'lilly'}
setFlower2 = {'jasmine','lotus', 'lilly'}
#union
#The union() method returns a new set with all items from both sets:
setUnion = setFlower1.union(setFlower2)
print("type(setFlower1) = {0} :  type(setFlower2) = {1}  :  setFlower1.union(setFlower2) = {2}".format(type(setFlower1),type(setFlower2), setUnion))

#update
#The update() method inserts the items in setFlower2 into setFlower1:
setFlower1.update(setFlower2)
print("type(setFlower1) = {0} :  type(setFlower2) = {1}  :  setFlower1.update(setFlower2) = {2}".format(type(setFlower1),type(setFlower2), setFlower1))


#Joint two sets
setCharacters = {'a','b','c','d','e','f'}
setNumbers = {1,2,3,4,5,6,7}
#union
setUnionResult = setCharacters.union(setNumbers)
print("setCharacters.union(setNumbers) : {0}".format(setUnionResult))

#set with list - union
listTemp = ['one','two']
setMovies = {'spider man', 'behind the enimies line'}
res =  setMovies.union(listTemp)
print ("Test code : {0}".format(res ))


#update
#insert items in set1 to set 2
setCharacters.update(setNumbers)
print("setCharacters.update(setNumbers) : {0}".format(setCharacters))

#keeping only duplicates
setCompanies1 = {'google', 'amazon', 'facebook'}
setCompanies2 = {'google','GRT', 'abirami'}
setCompanies1.intersection_update(setCompanies2)
print("setCompanies1.intersection_update(setCompanies2) : {0}".format(setCompanies1))


#intersection
setCompanies1 = {'google', 'amazon', 'facebook'}
setCompanies2 = {'google','GRT', 'abirami'}
newSet = setCompanies1.intersection(setCompanies2)
print("setCompanies1.intersection(setCompanies2) : {0}".format(newSet))


#Keep All, But NOT the Duplicates
x = {"apple", "banana", "cherry"}
y = {"google", "microsoft", "apple"}
x.symmetric_difference_update(y)
print("x.symmetric_difference_update(y) : {0}".format(x))


#symmetric_difference() method will return a new set,
# that contains only the elements that are NOT present in both sets.
x = {"apple", "banana", "cherry"}
y = {"google", "microsoft", "apple"}

z = x.symmetric_difference(y)
print("x.symmetric_difference(y) : {0}".format(z))

