package com.cursosdedesarrollo.h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by pepesan on 16/5/16.
 */
public class JDBCH2File {

    public static void main(String[] argv) {

        System.out.println("-------- H2 JDBC Connection Testing ------------");

        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your H2 JDBC Driver?");
            e.printStackTrace();
            return;
        }

        System.out.println("H2 JDBC Driver Registered!");
        Connection connection = null;

        try {
            connection = DriverManager
                    .getConnection("jdbc:h2:./test", "sa", "");

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