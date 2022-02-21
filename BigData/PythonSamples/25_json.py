import json

#convert from json to python - use json.loads()

x = '{"name" : "john", "age":16}'
#parse x
y = json.loads(x)
print(y)
print(y['name'])

#convert python to json

x = {
    "name": "John",
    "age": 30,
    "city": "New York"
}

y = json.dumps(x)

print(x)

#examples
print(json.dumps({"name": "John", "age": 30}))
print(json.dumps(["apple", "bananas"]))
print(json.dumps(("apple", "bananas")))
print(json.dumps("hello"))
print(json.dumps(42))
print(json.dumps(31.76))
print(json.dumps(True))
print(json.dumps(False))
print(json.dumps(None))

x = {
  "name": "John",
  "age": 30,
  "married": True,
  "divorced": False,
  "children": ("Ann","Billy"),
  "pets": None,
  "cars": [
    {"model": "BMW 230", "mpg": 27.5},
    {"model": "Ford Edge", "mpg": 24.1}
  ]
}

print(json.dumps(x))
#indent parameter to define the numbers of indents
print("json.dumps(x, indent=4)")
print(json.dumps(x, indent=4))

#define the separators, default value is (", ", ": "),
# which means using a comma and a space to separate each object,
# and a colon and a space to separate keys from values:
print("json.dumps(x, indent=4), separators=(\". \", \" = \")")
print(json.dumps(x, indent=4, separators=(". ", " = ")))

#order the result
# json.dumps(x, indent=4, sort_keys=True)