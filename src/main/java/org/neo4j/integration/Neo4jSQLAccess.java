package org.neo4j.integration;

import java.sql.*;

public class Neo4jSQLAccess {
    static final String JDBC_DRIVER = "com.simba.neo4j.jdbc.Driver";
    static final String DB_URL = "jdbc:neo4j://localhost:7687";

    //  Database credentials
    static final String USER = "neo4j";
    static final String PASS = "admin";

    public static void firstRecordFrom(Connection conn, String table) throws SQLException {
        System.out.println("Querying table " + table);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM " + table + " LIMIT 1");

        ResultSetMetaData md = rs.getMetaData();
        int cc = md.getColumnCount();

        while(rs.next()) {
            for (int x=1; x<cc; x++) {
                String label = md.getColumnLabel(x);
                String typeName = md.getColumnTypeName(x);
                String val = rs.getString(x);

                System.out.println("COLUMN " + label + " type " + typeName + " value " + val);
            }
        }

        rs.close();
        stmt.close();
    }

    public static void main(String[] args) throws Exception {
        Connection conn = null;
        Statement stmt = null;

        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            DatabaseMetaData md = conn.getMetaData();
            ResultSet rs = md.getTables(null, null, null, null);

            while(rs.next()) {
                String schema = rs.getString("TABLE_SCHEM");
                String name = rs.getString("TABLE_NAME");

                System.out.println("\n\nThis connection has a table named " + schema + "." + name);

                firstRecordFrom(conn, name);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {

            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } //end finally try
        } //end try
        System.out.println("Goodbye!");
    }
}
