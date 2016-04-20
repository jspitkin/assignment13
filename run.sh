#!/bin/bash
mkdir classes
javac -d classes *.java
java -cp classes/ assignment13.FindBestPathTester commands
rm -rf classes/
