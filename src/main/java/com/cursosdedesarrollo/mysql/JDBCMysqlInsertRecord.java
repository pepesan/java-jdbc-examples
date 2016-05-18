package com.cursosdedesarrollo.mysql;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by pepesan on 16/5/16.
 */
public class JDBCMysqlInsertRecord {

    private static Statement statement;
    private static final DateFormat dateFormat = new SimpleDateFormat(
            "yyyy/MM/dd HH:mm:ss");

    public static void main(String[] argv) {

        System.out.println("-------- MySQL JDBC Connection Testing ------------");

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return;
        }

        System.out.println("MySQL JDBC Driver Registered!");
        Connection connection = null;

        try {
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/demo","root", "");
             statement= null;


            String insertTableSQL = "INSERT INTO DBUSER"
                    + "(USERNAME, CREATED_BY, CREATED_DATE) " + "VALUES"
                    + "('pepesan','system', CURRENT_DATE())";

            try {

                statement = connection.createStatement();

                System.out.println(insertTableSQL);

                // execute insert SQL stetement
                statement.executeUpdate(insertTableSQL);

                System.out.println("Record is inserted into DBUSER table!");
                System.out.println("Records updated:"+statement.getUpdateCount());
                ResultSet rs=statement.executeQuery("select LAST_INSERT_ID()");
                rs.next();
                Integer lastid = rs.getInt(1);
                System.out.println("Insert ID:"+lastid);


            } catch (SQLException e) {

                System.out.println(e.getMessage());

            } finally {

                if (statement != null) {
                    statement.close();
                }

                if (connection != null) {
                    connection.close();
                }

            }

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            System.out.println("You made it, take control your database now!");

        } else {
            System.out.println("Failed to make connection!");
        }
    }

    private static String getCurrentTimeStamp() {

        java.util.Date today = new java.util.Date();
        return dateFormat.format(today.getTime());

    }
}