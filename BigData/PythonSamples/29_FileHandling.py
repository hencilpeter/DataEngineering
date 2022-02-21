#reading file

def customReadFileUsingRead(fileName):
    try:
        file = open(fileName)
        # similar code below
        # file = open("demofile.txt", "rt")
        print("Read file contents using read method : ")
        contents = file.read()
        return contents
    except:
        raise Exception("Exception raised while reading the file..")
    finally:
        file.close()

def customReadFileUsingReadLines(fileName):
    try:
        file = open(fileName)
        # similar code below
        # file = open("demofile.txt", "rt")
        print("Read file contents using read method : ")
        contentsInLines = file.readlines()
        return contentsInLines
    except:
        raise Exception("Exception raised while reading the file..")
    finally:
        file.close()

content = customReadFileUsingRead("samplefile.txt")
print(f"using read : {content}")
content = customReadFileUsingReadLines("samplefile.txt")
print(f"using readLines : {content}")


#read only part of file  using read command
file = open("samplefile.txt")
print("Read only 10 characters from a file using read method : ")
print(file.read(10))
file.close()

#read few line file  using readline command
file = open("samplefile.txt")
print("Read only 1 line using readline method : ")
print(file.readline())

#write/create file using mode w

def customFileWriter(fileName, mode, content ):
    file = open(fileName, mode)
    file.write(content)
    file.close()

customFileWriter("demofile2.txt", "w", "This is my first line.\n")
customFileWriter("demofile2.txt", "w", "this is my second line.\n")
customFileWriter("demofile2.txt", "a", "this is my third line.\n")
customFileWriter("demofile3.txt", "a", "this is my third line.\n")
#customFileWriter("demofile4.txt", "x", "this is my third line.\n")

def printFileContents(filename):
    file = open(filename)
    print(file.readlines())
    file.close()

printFileContents("demofile2.txt")

#delete file
import os
#os.remove("demofile4.txt")


#check file if exists
#if os.path.exists("demofile.txt"):
#  os.remove("demofile.txt")
#else:
#  print("The file does not exist")


#deletes folder
#os.rmdir("myfolder")