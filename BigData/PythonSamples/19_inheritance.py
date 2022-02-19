#create parent class
print("Parent class Person with properties firstname and lastname.")
class Person:
    def __init__(self, firstname, lastname):
        self.firstname = firstname
        self.lastname = lastname

    def printname(self):
        print(self.lastname, self.firstname)

objPerson = Person("John", "Dan")
objPerson.printname()

#while creating a class that inherits the functionality from another class,
#send the parent class as parameter when creating the child class.

#create child class but don't want to add any new properties
class Student(Person):
    pass

objStudent = Student("Mike", "tyson")
objStudent.printname()

#create child class and add properties
#add the __init__ function if we want to add properties (instead of pass)
class Student(Person):
    def __init__(self, firstname, lastname, givenname):
        Person.__init__(firstname, lastname)
        self.givenname = givenname

#instead of parent class name super can be used
class Student(Person):
    def __init__(self, firstname, lastname, givenname):
        super().__init__(firstname, lastname)
        self.givenname = givenname

    def printname(self):
        super().printname()
        print(f"{self.givenname}")

objStudent = Student("english", "tamil", "malayalam")
objStudent.printname()