#iterator example

fruitsTuple = ("apple", "orange", "cherry")
itFruit = iter(fruitsTuple)

print(next(itFruit))
print(next(itFruit))
print(next(itFruit))
#print(next(itFruit)) - will throw StopIteration exception

myString = "banana"
itChar = iter(myString)
print(next(itChar))
print(next(itChar))
print(next(itChar))
print(next(itChar))

#Creating iterator
#use __iter__() and __next__() functions
class FiveNumbers:
    def __iter__(self):
        self.n = 1
        return self

    def __next__(self):
        if self.n <= 5:
            number = self.n
            self.n += 1
            return number
        else:
            raise StopIteration



objNumber = FiveNumbers()
iterNumber = iter(objNumber)
print("Iterate class object...")
print(next(iterNumber))
print(next(iterNumber))
print(next(iterNumber))
print(next(iterNumber))
print(next(iterNumber))
print(next(iterNumber))
print(next(iterNumber))



