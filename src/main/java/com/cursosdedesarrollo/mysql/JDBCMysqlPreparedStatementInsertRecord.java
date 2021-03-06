package com.cursosdedesarrollo.mysql;

import com.cursosdedesarrollo.beans.User;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by pepesan on 16/5/16.
 */
public class JDBCMysqlPreparedStatementInsertRecord {

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
                    + "(USERNAME, CREATED_BY, CREATED_DATE) VALUES"
                    + "(?,?,?)";

            try {
                User user=new User(null,"pepesan","system",new Date(2016,2,26));
                preparedStatement = connection.prepareStatement(insertTableSQL);


                preparedStatement.setString(1, user.getUSERNAME());
                preparedStatement.setString(2, user.getCREATED_BY());
                preparedStatement.setDate(3,user.getCREATED_DATE());

                // execute insert SQL stetement
                preparedStatement.executeUpdate();

                System.out.println("Record is inserted into DBUSER table!");
                preparedStatement = connection.prepareStatement("select LAST_INSERT_ID()");
                ResultSet rs=preparedStatement.executeQuery();
                rs.next();
                Integer lastid = rs.getInt(1);
                rs.close();
                System.out.println("Insert ID:"+lastid);

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