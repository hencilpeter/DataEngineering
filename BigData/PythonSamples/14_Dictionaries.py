
#simple dictionary
dictAddress = {'name':'Tom Brenner', 'address' : 'hilsbro, usa', 'age':45}
print("print the whole dictionary : {0}".format(dictAddress))
print("print dictionary item : {0}".format(dictAddress['name']))
#len - print the no. of items.
#type() - data type


#access the values
#approach 1 - call DOES throw if the key does not exist
print('dictAddress[\'name\']  : {0}'.format(dictAddress['name']))
#approach 2 - call does not throw if the key does not exist
print('dictAddress.get(\'name\') : {0}'.format(dictAddress.get('name')))

#print keys
print('dictAddress.keys() : {0}'.format(dictAddress.keys()))

#print values
print('dictAddress.values() : {0}'.format(dictAddress.values()))

#update value
dictAddress['name'] = 'Dan Basset'
dictAddress['weight'] = 50
print('Print Values after change : {0}'.format(dictAddress.values()))

#return the key value pair as list
print('dictAddress.items() : {0}'.format(dictAddress.items()))


if 'name' in dictAddress:
    print('name key exist...')


#update values
#approach 1 : use []
#approach 2: call update method with key value pair
dictAddress.update({'name':'Sanjay Ramasamy'})
print('print values after update: {0}'.format(dictAddress.values()))

#add new item
#either use [] or update with new key ?

#remove items from dictionary
# use pop - remove items with the specified key
#popitem() method removes the last inserted item
#The del keyword removes the item with the specified key name
del dictAddress['age']
#del dictAddress will delete the dictionary itself. later wec can not use it.
#clear with empties the dictionary


#loop dictionaries
#Print all key names in the dictionary, one by one:
print('Print all the keys one by one :')
for key in dictAddress:
    print(key)

print('print all values :')
for key in dictAddress:
    print(dictAddress[key])
print('--------------------------')
for value in dictAddress.values():
    print(value)
#looping key value pair
for x, y in dictAddress.items():
  print(x, y)

#copy dictionary
#1. use .copy method
#2. dict() construtor

#Nested dictionaries
myfamily = {
  "child1" : {
    "name" : "Emil",
    "year" : 2004
  },
  "child2" : {
    "name" : "Tobias",
    "year" : 2007
  },
  "child3" : {
    "name" : "Linus",
    "year" : 2011
  }
}

#Nested dictionaries
child1 = {
  "name" : "Emil",
  "year" : 2004
}
child2 = {
  "name" : "Tobias",
  "year" : 2007
}
child3 = {
  "name" : "Linus",
  "year" : 2011
}

myfamily = {
  "child1" : child1,
  "child2" : child2,
  "child3" : child3
}