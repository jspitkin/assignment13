import csv

dataFile = "2015-q3"

summaryFile = []
summaryStats = {}

with open("../data/market/" + dataFile + ".csv") as inp:
    reader = csv.reader(inp)
    rowCount = 0
    for row in reader:
        if rowCount == 0:
            summaryFile.append(row[0:3])
        else:
            lookup = row[0] + '-' + row[1]
            if lookup in summaryStats:
                summaryStats[lookup].append(float(row[2]))
            else:
                summaryStats[lookup] = [float(row[2])]
        rowCount += 1

for lookup in sorted(summaryStats):
    row = lookup.split("-")
    data = summaryStats[lookup]
    avg = sum(data) / len(data)
    row.append(round(avg, 2))
    summaryFile.append(row)

with open("../data/market/" + dataFile + "-summary.csv", "w") as output:
    writer = csv.writer(output)
    for row in summaryFile:
        writer.writerow(row)
