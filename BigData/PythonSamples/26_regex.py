import re

sampleText = "Singapore is an asian country. Singapore has four national languages, namely, tamil, english, malay and mandarine."

#1.search the string to see if it start wtih "Sin" and ends with "ine."
print("search the string to see if it start wtih \"Sin\" and ends with \"ine.")
print(f"Given Text: #{sampleText}#")
searchPattern = "^Sin.*ine.$"
matchObject = re.search(searchPattern, sampleText)

if matchObject:
    print("Match exist")
    print(matchObject)
else:
    print("Match not exist")


#2. find all function
#find all matches
findAllResult = re.findall("Singapore", sampleText)
print("re.findall => {}".format(findAllResult))

#3. search function
searchResult = re.search("country", sampleText)
print(" re.search(\"country\", sampleText) ==> {}".format( searchResult))


#4. split function
print("split at each white space character.")
splitResult = re.split("\s", sampleText)
print(splitResult)

#5. sub function. replace every white-space character wtih the number 9
subRessult = re.sub("\s", "9", sampleText)
print ("re.sub(\"\s\", \"9\", sampleText) => {} ".format(subRessult))

subRessult = re.sub("\s", "9", sampleText, 3)
print ("re.sub(\"\s\", \"9\", sampleText, 3) => {} ".format(subRessult))


#6. match object example
matchObject = re.search("Singapore", sampleText)
if matchObject:
    print("matchObject.span() => {}".format(matchObject.span()))
    print("matchObject.string => {} ".format(matchObject.string))
    print("matchObject.group() => {}".format(matchObject.group()))