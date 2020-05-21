# Neo4j-SQL-Java

A simple worked example of how to communicate with Neo4j using SQL inside of a Java Program.

This sample program requires that you download the [Neo4j BI Connector](https://neo4j.com/bi-connector/)
package, that you uncompress it, and include the JDBC JAR file in your classpath.  An example
will be provided.

The code in this repo simply connects to a Neo4j instance, and gives an inventory of every
table that it can see, along with the first record from that table.

## Setup

Edit the Neo4jSQLAccess class, and customize your connection details at the top; the JDBC
URI, username, and password for connecting to your Neo4j instance.

Compile the code with:

```
mvn package
```

## Run

Adjust the path to your instance of the Neo4j BI Connector JAR, and run the code like this:

```
export BI_CONNECTOR="/path/to/Neo4jJDBC42.jar" 
java -cp "$BI_CONNECTOR:./target/neo4j-sql-java-1.0-SNAPSHOT.jar" org.neo4j.integration.Neo4jSQLAccess
```

## Sample Output

```
$ java -cp "$BI_CONNECTOR:./target/neo4j-sql-java-1.0-SNAPSHOT.jar" org.neo4j.integration.Neo4jSQLAccess
Connecting to database...
log4j:WARN No appenders could be found for logger (com.simba.neo4j.shaded.neo4j.driver.internal.shaded.io.netty.util.internal.logging.InternalLoggerFactory).
log4j:WARN Please initialize the log4j system properly.
log4j:WARN See http://logging.apache.org/log4j/1.2/faq.html#noconfig for more info.
May 21, 2020 9:54:10 AM com.simba.neo4j.shaded.neo4j.driver.internal.logging.JULogger info
INFO: Routing driver instance 2006034581 created for server address localhost:7687


This connection has a table named Node.Address
Querying table Address
COLUMN _NodeId_ type BIGINT value 3
COLUMN state type VARCHAR value VA
COLUMN street type VARCHAR value 123 Elm St


This connection has a table named Node.Phone
Querying table Phone
COLUMN _NodeId_ type BIGINT value 1


This connection has a table named Node.Post
Querying table Post
COLUMN _NodeId_ type BIGINT value 4


This connection has a table named Node.SSN
Querying table SSN
COLUMN _NodeId_ type BIGINT value 2


This connection has a table named Node.User
Querying table User
COLUMN _NodeId_ type BIGINT value 0
```