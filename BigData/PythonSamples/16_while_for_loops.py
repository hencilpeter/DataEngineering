
#while loop
#1.simple while loop
print("1.simple while loop")
count=0
while count < 5:
    count += 1
    print(count)

#2. while with break
print("2. while with break")
count=0
while count < 5:
    count += 1
    print(count)
    if count == 2:
        break;


#3. while with continue
print("3. while with continue")
count=0
while count < 5:
    count += 1
    if count == 2:
        continue; #skip printing 2
    print(count)

#4. while with else
print("3. while with else")
count=6
while count < 5:
    count += 1
    if count == 2:
        continue; #skip printing 2
    print(count)
else:
    print("while loop is not executed...else block executed.")

#for loops
fruits = ["apple", "banana", "cherry"]
#1. simple for loop
print('1. simple for loop')
for item in fruits:
    print(item)

#for - else
#for break
#for continue
#for with pass

#range function
print("for loop with range function")
for x in range(5):#0 to 4
    print(x)