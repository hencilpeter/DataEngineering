#python tuples
#tuple creation
tupleFruits = ('apple', 'orange', 'cherry', 'mango')
print("type = {0} : tupleFruits = {1}".format(type(tupleFruits), tupleFruits))
#tuple allows duplicates
tupleFruitsWithDuplicates = ('apple', 'orange', 'cherry', 'mango', 'orange')
print("type = {0} : tupleFruitsWithDuplicates = {1}".format(type(tupleFruitsWithDuplicates), tupleFruitsWithDuplicates))
#tuple length
print("type = {0} : len(tupleFruitsWithDuplicates) = {1}".format(type(tupleFruitsWithDuplicates), len(tupleFruitsWithDuplicates)))

#tuple with multiple type items
tupleMultipleTypeItems = ("apple", 15, True)
print("type = {0} : tupleMultipleTypeItems = {1}".format(type(tupleMultipleTypeItems), tupleMultipleTypeItems))

#tuple constructor - two brackets with tuple()
tupleCountries = tuple(("India", "Singapore", "Australia"))
print("type = {0} : tupleCountries = {1}".format(type(tupleCountries), tupleCountries))

#access tuple values
print("type = {0} : tupleCountries[1] = {1}".format(type(tupleCountries), tupleCountries[1]))

#negative indexing, index range, slicing - all works

#using if condition in tuple
if "India" in tupleCountries:
    print("Yes. India exist in the given tuple")

#update tuple valeus
#change to list, update and make tuple
lstCountries = list(tupleCountries)
lstCountries.append("England")
tupleCountries = tuple(lstCountries)
print("Tuple after adding \"England\" : {0}".format(tupleCountries))
#similarly uppend the values

#unpack tuples
tupleCities = ('Chennai','Bangalore')
(ctyChennai, ctyBangalore) = tupleCities
# or ctyChennai, ctyBangalore = tupleCities
print("type = {0} : ctyChennai = {1}".format(type(ctyChennai), ctyChennai))

#packing
tupleDuplicateCities = (ctyChennai, ctyBangalore)
print("packing example...")
print(" Tuple packing : {0}".format(tupleDuplicateCities))

#loop
#for-in
#for using range(len())
#while also change be used


#Join tuples
#Add tuples
tupleAfricanCountries = ("Jamaica", 'Ethiyopia')
tupleAllCountries = tupleCountries + tupleAfricanCountries
print("All Countries tuple : {0}".format(tupleAllCountries))


#multiply
tupleKings = ("akbar", "ashoka")
tupleKings *= 3
print("tupleKings *= 2  : {0}".format(tupleKings))
