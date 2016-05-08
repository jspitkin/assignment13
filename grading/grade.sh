#/bin/bash
mkdir classes
javac -d classes *.java
java -cp .:classes:/Library/Java/Extensions autograderutils.JUnitPlugin assignment13.Assignment13GradingTests groups.properties extra.properties
rm -rf classes
