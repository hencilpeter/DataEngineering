import datetime

#print the current date time
print (f"current date time : {datetime.datetime.now()}")

x = datetime.datetime.now()
print(x.year) # print the year
print(x.strftime("%A")) #print the Weekday


#crete date object
x = datetime.datetime(2020, 3, 2)
print(x)

#strftime method for formatting the date object into readble string
x = datetime.datetime(2020, 3, 2)
print(x.strftime("%B")) # Month name