package com.cursosdedesarrollo.h2;

import java.sql.*;

/**
 * Created by pepesan on 16/5/16.
 */
public class JDBCH2InMemory {

    static Connection connection = null;
    static PreparedStatement  createPreparedStatement;
    static PreparedStatement insertPreparedStatement;
    static PreparedStatement selectPreparedStatement;

    static String CreateQuery = "CREATE TABLE PERSON(id int primary key, name varchar(255))";
    static String InsertQuery = "INSERT INTO PERSON" + "(id, name) values" + "(?,?)";
    static String SelectQuery = "select * from PERSON";

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


        try {
            connection = DriverManager
                    .getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "");
            try {
                connection.setAutoCommit(false);

                createPreparedStatement = connection.prepareStatement(CreateQuery);
                createPreparedStatement.executeUpdate();
                createPreparedStatement.close();

                insertPreparedStatement = connection.prepareStatement(InsertQuery);
                insertPreparedStatement.setInt(1, 1);
                insertPreparedStatement.setString(2, "Jose");
                insertPreparedStatement.executeUpdate();
                insertPreparedStatement.close();

                selectPreparedStatement = connection.prepareStatement(SelectQuery);
                ResultSet rs = selectPreparedStatement.executeQuery();
                System.out.println("H2 In-Memory Database inserted through PreparedStatement");
                while (rs.next()) {
                    System.out.println("Id " + rs.getInt("id") + " Name " + rs.getString("name"));
                }
                selectPreparedStatement.close();

                connection.commit();
            } catch (SQLException e) {
                System.out.println("Exception Message " + e.getLocalizedMessage());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                connection.close();
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
    private static void insertWithStatement() throws SQLException {

        Statement stmt = null;
        try {
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            stmt.execute("CREATE TABLE PERSON(id int primary key, name varchar(255))");
            stmt.execute("INSERT INTO PERSON(id, name) VALUES(1, 'Anju')");
            stmt.execute("INSERT INTO PERSON(id, name) VALUES(2, 'Sonia')");
            stmt.execute("INSERT INTO PERSON(id, name) VALUES(3, 'Asha')");

            ResultSet rs = stmt.executeQuery("select * from PERSON");
            System.out.println("H2 In-Memory Database inserted through Statement");
            while (rs.next()) {
                System.out.println("Id " + rs.getInt("id") + " Name " + rs.getString("name"));
            }

            stmt.execute("DROP TABLE PERSON");
            stmt.close();
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

}