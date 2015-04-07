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
leo@localhost:~/p/h/amex-assignments git:master ❯❯❯ cd assignment1
leo@localhost:~/p/h/a/assignment1 git:master ❯❯❯ ls target                                                       
classes  dependency-maven-plugin-markers  maven-archiver  surefire-reports  test-classes  assignment1-1.0-SNAPSHOT.jar
leo@localhost:~/p/h/a/assignment1 git:master ❯❯❯
```

```
Usage:
    App [-i <input CSV>] [-o <output CSV>] <number of records to generate>

            -i <input CSV>  : Used when reading from a CSV. Provides input CSV path
            -o <output CSV> : Used when writing to a CSV. Provides output CSV path
            <number of records to generate> : Only required with -o.

            Note: either -o or -i can be used, but not both at the same time.
```

## Examples
The jar file is already built with all the necessary dependencies. We can now run it as a normal jar:
```
/usr/java/jdk1.7.0_71/bin/java -jar assignment1/target/assignment1-1.0-SNAPSHOT.jar -o <output file> <number of test data records to generate>
```

Or to read CSV input:
```
/usr/java/jdk1.7.0_71/bin/java -jar assignment1/target/assignment1-1.0-SNAPSHOT.jar -i <input file>
```

