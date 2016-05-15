package com.cursosdedesarrollo.mysql;

import java.sql.*;

/**
 * Created by pepesan on 16/5/16.
 */
public class JDBCMysqlPreparedStatement {

    private static PreparedStatement preparedStatement = null;

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
                    .getConnection("jdbc:mysql://localhost:3306/test","root", "");
            String createTableSQL = "CREATE TABLE DBUSER1("
                    + "USER_ID INTEGER (5) NOT NULL AUTO_INCREMENT, "
                    + "USERNAME VARCHAR(20) NOT NULL, "
                    + "CREATED_BY VARCHAR(20) NOT NULL, "
                    + "CREATED_DATE DATE NOT NULL, " + "PRIMARY KEY (USER_ID) "
                    + ")";
            try {

                preparedStatement = connection.prepareStatement(createTableSQL);

                System.out.println(createTableSQL);
                // execute the SQL stetement
                preparedStatement.executeUpdate();

                System.out.println("Table \"DBUSER1\" is created!");

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
}