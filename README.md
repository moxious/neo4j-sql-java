# Neo4j-SQL-Java

A simple worked example of how to communicate with Neo4j using SQL inside of a Java Program.

This sample program requires that you download the [Neo4j BI Connector](https://neo4j.com/bi-connector/)
package, that you uncompress it, and include the JDBC JAR file in your classpath.  An example
will be provided.

## Setup

Clone the repo, and then compile the code with:

```
mvn package
```

## Run

Adjust the path to your instance of the Neo4j BI Connector JAR, and run the code like this:

```
export BI_CONNECTOR="/path/to/Neo4jJDBC42.jar" 
java -cp "$BI_CONNECTOR:./target/neo4j-sql-java-1.0-SNAPSHOT.jar" org.neo4j.integration.Neo4jSQLAccess
```