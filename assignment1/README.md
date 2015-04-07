# Assignment 1

## Description

Generates CSV Test data. Once a CSV file is created, the application then be used to read the CSV data and index it within a Redis
database for faster access to an in memory map.

## Usage

First, build the application

```
mvn install
```

This will create a jar file (see below):
```
leo@localhost:~/p/h/a/assignment1 git:master ❯❯❯ ls target                                                       
classes  dependency-maven-plugin-markers  maven-archiver  surefire-reports  test-classes  assignment1-1.0-SNAPSHOT.jar
leo@localhost:~/p/h/a/assignment1 git:master ❯❯❯
```

The jar file is already built with all the necessary dependencies. We can now run it as a normal jar:
```
/usr/java/jdk1.7.0_71/bin/java -jar assignment1/target/assignment1-1.0-SNAPSHOT.jar -o <output file> -c <number of test data records to generate>
```
