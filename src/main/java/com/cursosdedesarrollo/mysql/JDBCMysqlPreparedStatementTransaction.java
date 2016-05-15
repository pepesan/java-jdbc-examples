package com.cursosdedesarrollo.mysql;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by pepesan on 16/5/16.
 */
public class JDBCMysqlPreparedStatementTransaction {

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


            PreparedStatement preparedStatementInsert = null;
            PreparedStatement preparedStatementUpdate = null;

            String insertTableSQL = "INSERT INTO DBUSER"
                    + "(USERNAME, CREATED_BY, CREATED_DATE) VALUES"
                    + "(?,?,?)";

            String updateTableSQL = "UPDATE DBUSER SET USERNAME =? "
                    + "WHERE USER_ID = ?";

            try {


                connection.setAutoCommit(false);

                preparedStatementInsert = connection.prepareStatement(insertTableSQL);

                preparedStatementInsert.setString(1, "pepesan101");
                preparedStatementInsert.setString(2, "system");
                preparedStatementInsert.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
                preparedStatementInsert.executeUpdate();

                preparedStatementUpdate = connection.prepareStatement(updateTableSQL);
                // preparedStatementUpdate.setString(1,
                // "A very very long string caused db error");
                preparedStatementUpdate.setString(1, "new string");
                preparedStatementUpdate.setString(2, "pepesan101");
                preparedStatementUpdate.executeUpdate();

                connection.commit();

                System.out.println("Done!");

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