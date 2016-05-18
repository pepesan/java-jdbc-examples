package com.cursosdedesarrollo.mysql;

import com.cursosdedesarrollo.beans.User;

import javax.jws.soap.SOAPBinding;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pepesan on 16/5/16.
 */
public class JDBCMysqlPreparedStatementSelectRecords {

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
                    .getConnection("jdbc:mysql://localhost:3306/test","root", "");


            String selectSQL = "SELECT USER_ID, USERNAME,CREATED_BY,CREATED_DATE FROM DBUSER1 WHERE USERNAME = ?";

            try {
                preparedStatement = connection.prepareStatement(selectSQL);
                preparedStatement.setString(1, "pepesan");

                // execute select SQL stetement
                ResultSet rs = preparedStatement.executeQuery();
                List<User> listado=new ArrayList<User>();
                while (rs.next()) {

                    Integer userid = rs.getInt("USER_ID");
                    String username = rs.getString("USERNAME");
                    String created_by = rs.getString("CREATED_BY");
                    Date created_date = rs.getDate("CREATED_DATE");
                    User user=new User();
                    user.setUSER_ID(userid);
                    user.setUSERNAME(username);
                    user.setCREATED_BY(created_by);
                    user.setCREATED_DATE(created_date);
                    System.out.println("userid : " + userid);
                    System.out.println("username : " + username);
                    System.out.println("created_by : " + created_by);
                    System.out.println("created_date : " + created_date);
                    System.out.println("user : " + user);
                    listado.add(user);
                }
                System.out.println(listado);
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