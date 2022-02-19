#
username = input("Enter username:")
print("username is : " + username)

price = 49
txt = "The price is {:.2f} dollars"
print(txt.format(price))

#index numbers
age = 36
name = "John"
txt = "His name is {1}. {1} is {0} years old."
print(txt.format(age, name))

#names indexes
orderFormatting = "I have a {watchColour} colour Watch and {shoeColour} colour Shoe"
print(orderFormatting.format(shoeColour="brown", watchColour="black"))