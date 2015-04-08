# Assignment 2

## Usage
```
Usage:
    com.github.r351574nc3.amex.assignment2.App -t <test data.arff> -i <input data.arff> -o <output arff> 

            -t <test data.arff>   : Test data used for training and testing the model.
            -i <input data.arff>  : Unlabeled dataset
            -o <output data.arff> : Path where results will be output
```

## Examples
To run, simply use:
```
java -jar assignment2/target/assignment2-1.0-SNAPSHOT.jar assignment2/src/main/resources/auto-mpg/auto-mpg.arff -t assignment2/src/main/resources/auto-mpg/auto-mpg.arff -i assignment2/src/main/resources/auto-mpg/auto-mpg.arff -o out.arf 
```

### Install Weka

1. Download weka zip file from http://prdownloads.sourceforge.net/weka/weka-3-7-12.zip
2. Uncompress weka zip file.
3. Maven install:
```
leo@localhost:~/p/h/a/assignment2 git:master ❯❯❯ mvn install:install-file -DartifactId=weka -DgroupId=nl.ac.waikato.cs.ml -Dversion=3.7.12 -Dpackaging=jar -DgeneratePom=true -Dfile=$HOME/weka-3-7-12/weka.jar
OpenJDK 64-Bit Server VM warning: ignoring option MaxPermSize=512m; support was removed in 8.0
[INFO] Scanning for projects...
[INFO]                                                                         
[INFO] ------------------------------------------------------------------------
[INFO] Building assignment2 1.0-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO] 
[INFO] --- maven-install-plugin:2.4:install-file (default-cli) @ assignment2 ---
[INFO] Installing /home/leo/weka-3-7-12/weka.jar to /home/leo/.m2/repository/nl/ac/waikato/cs/ml/weka/3.7.12/weka-3.7.12.jar
[INFO] Installing /tmp/mvninstall8797380203347745061.pom to /home/leo/.m2/repository/nl/ac/waikato/cs/ml/weka/3.7.12/weka-3.7.12.pom
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 0.417 s
[INFO] Finished at: 2015-04-07T08:43:51-07:00
[INFO] Final Memory: 8M/145M
[INFO] ------------------------------------------------------------------------

```
