###### FUNDUSZE INVESTYCYJNE  ######

### ABOUT ###
Application for calculating investment base on computing.

This Project has been migrated from java8 to java10, you can java10 version project here:
https://github.com/Krzysztof-Konopa/fundusze-inwestycyjne-java10

### REQUIREMENTS ###

Java 8 installed (or higher)
Maven 3 (3.3.9 used in project)

### BUILD ###
Run from project root directory:

mvn clean install

### RUN ###
Run from project root directory:

java -jar target/fundusze-1.0-SNAPSHOT.jar

navigate to localhost:8080/ in your browser

### HOW TO USE ###

1. Fill Cash field
2. Pick strategy from dropdown list
3. Fill table like in task examples
    - only type column is mandatory to fill
    - [Add] button adds row into table
    - [-] button removes row
4. Hit [Compute] button to compute result.
5. As a result you should see table with computed results below [Compute] button.

