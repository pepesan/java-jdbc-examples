package com.cursosdedesarrollo.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by pepesan on 16/5/16.
 */
public class JDBCMysqlCreateDatabase {

    private static Statement statement;

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
             statement= null;
            String createDatabaseSQL = "CREATE DATABASE `demo` " +
                    "CHARACTER SET utf8 COLLATE utf8_unicode_ci";
            try {

                statement = connection.createStatement();

                System.out.println(createDatabaseSQL);
                // execute the SQL stetement
                statement.execute(createDatabaseSQL);

                System.out.println("Database \"demo\" is created!");

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
}