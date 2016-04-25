import csv

flightFile = "flights-2015-q3"
reviews = "airline-reviews"
flightFile2 = flightFile + "v2"

flightHeader = []
flights = []
ratings = {}
newCol = ""

# read in reviews
with open("../" + reviews + ".csv", encoding="latin-1") as inp:
    reader = csv.reader(inp)
    rowCount = 0
    for row in reader:
        if rowCount == 0:
            newCol = row[1].upper()
        else:
            rating = 0
            # if no overall rating, average all other ratings
            if row[1] == "":
                otherRatings = []
                for i in range(2, 9):
                    if row[i] != "":
                        otherRatings.append(float(row[i]))
                if len(otherRatings) > 0:
                    rating = sum(otherRatings) / len(otherRatings)
                    # convert scale: [0, 5] --> [1, 10]
                    # do so by multiply by 9/5 and add one
                    rating = (rating * 9 / 5) + 1
            # store rating
            else:
                rating = float(row[1])
            if row[0] in ratings:
                ratings[row[0]].append(rating)
            else:
                ratings[row[0]] = [rating]
        rowCount += 1
    # average all ratings together for a look-up
    for rating in ratings:
        ratings[rating] = round(sum(ratings[rating]) / len(ratings[rating]), 2)

# read in dataset
with open("../" + flightFile + ".csv") as inp:
    reader = csv.reader(inp)
    rowCount = 0
    for row in reader:
        # swap columns, origin & destination first
        temp = row[0]
        row[0] = row[1]
        row[1] = row[2]
        row[2] = temp
        # store header
        if rowCount == 0:
            flightHeader = row
            flightHeader.append(newCol)
        # add data
        else:
            # first, fill in empty delays, assume zero
            if row[10] == "":
                row[10] = 0
            if row[11] == "":
                row[11] = 0
            if row[12] == "":
                row[12] = 0
            if row[13] == "":
                row[13] = 0
            if row[14] == "":
                row[14] = 0
            # fill in more empty delays, assume zero
            if row[3] == "":
                row[3] = 0
            if row[4] == "":
                row[4] = 0
            # lastly, fill in elapsed time based on delay
            if row[8] == "":
                sumDelay = float(row[4])
                if sumDelay != 0:
                    row[8] = float(row[7]) + sumDelay
                else:
                    row[8] = row[7]
            # format/simplify all numbers
            for i in range(3, 15):
                splitLine = str(row[i]).split(".")
                if len(splitLine) > 1 and float(splitLine[1]) == 0:
                    row[i] = splitLine[0]
            # add in rating
            if row[2] in ratings:
                row.append(ratings[row[2]])
            else:
                row.append("")
            # add row of data
            flights.append(row)
        rowCount += 1

# output new flight dataset
with open("../" + flightFile2 + ".csv", "w") as output:
    writer = csv.writer(output, lineterminator="\n")
    writer.writerow(flightHeader)
    for row in flights:
        writer.writerow(row)
