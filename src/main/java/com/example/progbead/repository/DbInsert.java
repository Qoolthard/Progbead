package com.example.progbead.repository;

import java.sql.*;

public class DbInsert {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/test";

    //  Database credentials
    static final String USER = "sa";
    static final String PASS = "";

    public static void main(String nama) {
        Connection conn = null;
        Statement stmt = null;
        try{
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            System.out.println("Csatlakozás az adatbázishoz...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            System.out.println("Sikeres csatlakozás az adatbázishoz...");

            // STEP 3: Execute a query
            stmt = conn.createStatement();
            String sql = "SELECT id, name FROM Registration";
            ResultSet rs = stmt.executeQuery(sql);
            int bigid = 0;
            // STEP 4: Extract data from result set
            while(rs.next()) {
                // Retrieve by column name
                int newid  = rs.getInt("id");
                if (newid > bigid) {
                    bigid = newid;
                }
            }
            // STEP 5: Clean-up environment
            rs.close();
            bigid++;
            sql = "INSERT INTO Registration " + "VALUES (" + bigid + ", '" + nama + "')";

            stmt.executeUpdate(sql);

        } catch(SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try
        System.out.println("Viszlát!");
    }
}