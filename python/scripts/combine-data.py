import csv

year = "2015"
dataFile = year + "-" + "09"
dataFile2 = year + "-" + "q3"

combinedFile = []
flightCosts = {}
newCol = ""

with open("../data/market/" + dataFile2 + "-summary.csv") as inp:
    reader = csv.reader(inp)
    rowCount = 0
    for row in reader:
        if rowCount == 0:
            newCol = row[2]
        else:
            lookup = row[0] + '-' + row[1]
            flightCosts[lookup] = row[2]
        rowCount += 1

with open("../data/ontime/" + dataFile + ".csv") as inp:
    reader = csv.reader(inp)
    rowCount = 0
    for row in reader:
        row = row[0:15]
        if rowCount == 0:
            row.append(newCol)
            combinedFile.append(row)
        else:
            lookup = row[1] + '-' + row[2]
            cost = flightCosts[lookup] if lookup in flightCosts else -1
            row.append(cost)
            combinedFile.append(row)
        rowCount += 1

with open("../data/ontime/" + dataFile + "c.csv", "w") as output:
    writer = csv.writer(output)
    for row in combinedFile:
        writer.writerow(row)
