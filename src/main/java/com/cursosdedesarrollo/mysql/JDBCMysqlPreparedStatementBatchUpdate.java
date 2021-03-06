package com.cursosdedesarrollo.mysql;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by pepesan on 16/5/16.
 */
public class JDBCMysqlPreparedStatementBatchUpdate {

    private static PreparedStatement preparedStatement;
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


            String insertTableSQL = "INSERT INTO DBUSER"
                    + "( USERNAME, CREATED_BY, CREATED_DATE) VALUES"
                    + "(?,?,?)";

            try {

                preparedStatement = connection.prepareStatement(insertTableSQL);

                connection.setAutoCommit(false);


                preparedStatement.setString(1, "pepesan101");
                preparedStatement.setString(2, "system");
                preparedStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
                preparedStatement.addBatch();


                preparedStatement.setString(1, "pepesan102");
                preparedStatement.setString(2, "system");
                preparedStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
                preparedStatement.addBatch();

                preparedStatement.setString(1, "pepesan103");
                preparedStatement.setString(2, "system");
                preparedStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
                preparedStatement.addBatch();

                preparedStatement.executeBatch();

                connection.commit();

                System.out.println("Record is inserted into DBUSER table!");
            } catch (SQLException e) {

                System.out.println(e.getMessage());

            } finally {

                if (preparedStatement != null) {
                    preparedStatement.close();
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