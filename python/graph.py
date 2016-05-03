# by Sean McKenna
#
# Python 3 script to aggregate & run dijkstra's algorithm on a weighted graph
#
# dataset is a collection of United States airport flights in Fall of 2015
# pickle data files allow quick access to running Dijkstra's algorithm
#
# simple API via Python interactive shell:
#     exec(open("graph.py").read())
#     dijkstra("SLC", "MVY", dataCols[3]).debug()
#
# runs Dijkstra's algorithm on airport distances between SLC & MVY
# see cost to all other airports via `airports[]` & more data in `dataCols[]`
# raw data gets imported into `flights` and aggregated data into `aggregates`
#
# can also run via the command line:
#     python graph.py SLC MVY DISTANCE B6
#


# necessary for args, csv import, pickle export, and dijkstra's priority queue
# queue requires the use of Python 3!
import sys
import csv
import pickle
import queue


# global variables for access via python shell
data = "../flights-2015-q3.csv"
header = []
dataCols = []
flights = []
airports = {}
epsilon = 0.000000000000000001

# dict of all origins, and each has a dict of all destinations! (4204 total)
# aggregates{ORIGIN}{DESTINATION} = AggregateFlight
aggregates = {}


# need to readFlights, createAggregates, & saveAggregates at least once
def main():
    # readFlights()
    # createAggregates()
    # saveAggregates()
    loadAggregates()

    # with command line arguments, allow simple output of Dijkstra's algorithm
    args = sys.argv
    if len(args) > 1:
        if len(args) > 4:
            result = dijkstra(args[1], args[2], args[3], args[4])
        else:
            result = dijkstra(args[1], args[2], args[3])
        result.debug()


# run dijkstra's algorithm with weight based on the given attribute
# computes cheapest cost to ALL airports currently
# optional switch to limit edges to a single carrier
def dijkstra(origin, destination, attribute, carrier=None):
    # globals
    global epsilon
    
    # reset airport nodes
    resetGraph()

    # initialize starting node
    start = airports[origin]
    start.cost = 0

    # add all airports to priority queue
    # Python's priority queue does LOWEST priority first
    # easiest to store cost as a tuple [0] = cost, [1] = airport
    pq = queue.PriorityQueue()
    for airport in airports:
        pq.put((airports[airport].cost, airport))

    # iterate through the priority queue to find all cheapest paths
    # could stop short, to only get cheapest path to destination
    # while not airports[destination].previous:
    while not pq.empty():
        
        # update epsilon for zero weight cycles with two criteria
        ignoreCycles = attribute in ["DELAY", "CANCELED"]
        if ignoreCycles:
            epsilon = 0
        
        # get cheapest in priority queue & iterate through its neighbors
        item = pq.get()
        cost = item[0]
        airport = item[1]
        if airport in aggregates:
            for neighbor in aggregates[airport]:

                # calculate cost for each neighbor
                additionalCost = getattr(aggregates[airport][neighbor], attribute)
                # there are some invalid (negative) numbers - set them to infinity!
                if additionalCost < 0:
                    additionalCost = float("inf")
                totalCost = cost + additionalCost

                # if carrier specified, infinite cost for carrier not in list
                if carrier is not None:
                    if carrier not in aggregates[airport][neighbor].carriers:
                        totalCost = float("inf")

                # update cost of each neighbor only if cheaper
                currentCost = airports[neighbor].cost
                if (totalCost - currentCost) < epsilon:
                    
                    if abs(totalCost - currentCost) < epsilon:
                        if airport not in airports[neighbor].previous:
                            airports[neighbor].previous.append(airport)
                    else:
                        airports[neighbor].cost = totalCost
                        airports[neighbor].previous = [airport]

                    # update priority queue as well!
                    # if already in queue, update its priority
                    temp = 0
                    updated = False
                    for a in pq.queue:
                        if a[1] == neighbor and totalCost < a[0]:
                            pq.queue[temp] = (totalCost, neighbor)
                            updated = True
                        temp += 1
                    # otherwise, if not in the queue, add back in!
                    if not updated:
                        pq.put((totalCost, neighbor))

    # create a DijkstrasResult object
    result = DijkstrasResult()
    node = airports[destination]
    result.set_cost(node.cost)

    # traceback our path
    # must handle when we have no valid result path too
    result.paths = findAllPaths(origin, destination)

    # return the DijkstrasResult object (paths & cost)
    # will be invalid path and infinite cost if impossible
    return result


# reset links between aggregate flights and weights
def resetGraph():
    global airports
    airports = {}
    for origin in aggregates:
        if origin not in airports:
            airport = Airport(origin)
            airports[origin] = airport
        for destination in aggregates[origin]:
            if destination not in airports:
                airport = Airport(destination)
                airports[destination] = airport


# iteratively find all valid paths from destination to source
# super hacky, but this turned out to be quite tricky to implement
def findAllPaths(source, destination):
    paths = []
    current = destination
    finalHops = []
    while airports[destination].previous:
        path = [destination]
        current = airports[destination].previous.pop()
        temp = destination
        addPath = True
        while current != source:
            addPath = True
            if airports[current].previous:
                airports[temp].previous.append(current)
                path.append(current)
                temp = current
                current = airports[current].previous.pop()
                if current == source:
                    finalHops.append(temp)
            elif current in finalHops:
                path.append(current)
                current = source
            else:
                current = source
                addPath = False
        if addPath:
            path.append(source)
            paths.append(path)
    # reverse paths
    for path in paths:
        path.reverse()
    # keep paths unique
    pathStrings = []
    finalPaths = []
    for path in paths:
        string = ""
        for airport in path:
            string += airport
        if string not in pathStrings:
            pathStrings.append(string)
            finalPaths.append(path)
    return finalPaths


# reads in all flights from the source data file
def readFlights():
    # read in flights data file
    global flights
    flights = []
    with open(data) as inp:
        reader = csv.reader(inp)
        rowCount = 0
        for row in reader:
            if rowCount is 0:
                for col in row:
                    header.append(col)
            else:
                flights.append(row)
            rowCount += 1

    # update header for data only
    global dataCols
    dataCols = []
    dataHeader = header[3:len(header)]
    for column in dataHeader:
        dataCols.append(column)


# parse dataset into the aggregate flight data structure: aggregates
def createAggregates():
    global aggregates
    aggregates = {}
    for flight in flights:

        # unique IDs for a flight
        origin = flight[0]
        destination = flight[1]

        # grab the carrier ID, will save in a list
        carrier = flight[2]

        # grab all numerical flight data
        flightData = flight[3:len(flight)]

        # create an attributes dictionary for this flight
        dataAttributes = {}
        dataPointer = 0
        for attr in dataCols:
            dataAttributes[attr] = flightData[dataPointer]
            dataPointer += 1

        # create an origin dict with a dest dict with one aggregate flight
        if origin not in aggregates:
            aggregate = AggregateFlight(origin, destination, carrier)
            for attr in dataAttributes:
                aggregate.add_attribute(attr, dataAttributes[attr])
            newDestDict = {}
            newDestDict[destination] = aggregate
            aggregates[origin] = newDestDict

        # else utilize an origin dictionary
        else:
            originDict = aggregates[origin]

            # create a dest dict with one aggregate flight
            if destination not in originDict:
                aggregate = AggregateFlight(origin, destination, carrier)
                for attr in dataAttributes:
                    aggregate.add_attribute(attr, dataAttributes[attr])
                originDict[destination] = aggregate

            # else utilize destination dictionary, increment aggregate flights
            else:
                aggregate = originDict[destination]
                aggregate.add_carrier(carrier)
                aggregate.add_new_flight(dataAttributes)


# saves aggregated data into pickle files, optimizes scripting
def saveAggregates():
    with open("dataCols.pickle", "wb") as p:
        pickle.dump(dataCols, p)
    with open("aggregates.pickle", "wb") as p:
        pickle.dump(aggregates, p)


# enables loading data from pickle files
def loadAggregates():
    with open("dataCols.pickle", "rb") as p:
        global dataCols
        dataCols = pickle.load(p)
    with open("aggregates.pickle", "rb") as p:
        global aggregates
        aggregates = pickle.load(p)


# save aggregates data file to csv
def saveAggregatesToCSV():
    output = []
    header = ["ORIGIN", "DESTINATION", "CARRIER"] + dataCols
    output.append(header)
    for origin in aggregates:
        for destination in aggregates[origin]:
            flight = aggregates[origin][destination]
            carrier = "TA"
            data = []
            for column in dataCols:
                data.append(getattr(flight, column))
            row = [origin, destination, carrier] + data
            output.append(row)
    
    with open("aggregates.csv", "w", newline="") as out:
        writer = csv.writer(out)
        for row in output:
            writer.writerow(row)

# Flight object, which contains an origin and destination
class Flight:
    origin = None
    destination = None

    def __init__(self, o, d):
        self.origin = Airport(o)
        self.destination = Airport(d)

    def add_attribute(self, attr_name, attr_value):
        setattr(self, attr_name, float(attr_value))


# AggregateFlight object, keeps a running tab of all flight data
# along an edge between two airports, like how many flights are there,
# which carriers are there, as well as a running average of all data
class AggregateFlight(Flight):
    carriers = []
    count = 0

    def __init__(self, o, d, c):
        super(AggregateFlight, self).__init__(o, d)
        self.carriers = [c]
        self.count = 1

    def add_carrier(self, c):
        if c not in self.carriers:
            self.carriers.append(c)

    def avg_attributes(self, attr_name, attr_value):
        avg_so_far = getattr(self, attr_name) * self.count
        setattr(self, attr_name, (avg_so_far + attr_value) / (self.count + 1))

    # add a dictionary of data attributes
    def add_new_flight(self, all_attributes):
        for attrib in all_attributes:
            self.avg_attributes(attrib, float(all_attributes[attrib]))
        self.count += 1


# Airport object, which contains a name and running cheapest cost
# as well as a pointer to the previous Airport until the starting point
class Airport:
    name = None
    cost = float("inf")
    previous = []

    def __init__(self, n):
        self.name = n

    def equal(self, airport):
        return airport.str() is self.name

    def str(self):
        return self.name


# stores data for results of Dijkstra's algorithm
# includes a string list of the path, and the total cost
# if cost is infinite ("INF") then no valid path exists
class DijkstrasResult:
    paths = [[]]
    cost = float("inf")

    def __init__(self):
        self.cost = 0

    def set_cost(self, c):
        self.cost = c

    def debug(self):
        for path in self.paths:
            print(path)
        print(self.cost)


if __name__ == '__main__':
    main()
