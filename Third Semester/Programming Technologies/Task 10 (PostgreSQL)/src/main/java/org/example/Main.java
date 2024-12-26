package org.example;

import java.sql.*;

// команды в постгре шелл:
// \! chcp 1251 - если траблы с кодировкой
// postgres=# \dt
// Отношения не найдены.
//        postgres=# CREATE TABLE students
//        postgres-# (
//        postgres(# id   serial, serial - если идентификатор не задаём, то генерируем автоматически
//        postgres(# name text,
//        postgres(# score        int
//        postgres(# );
// select * from students; - вывести всё

public class Main {
    private static Connection connection;
    private static Statement statement;
    public static void main(String[] args) {
        try {
            connect();
            ResultSet rs = statement.executeQuery("SELECT * FROM students"); // достать всё
            while (rs.next()) {
                System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }
    private static void connect() throws SQLException {
        try {
            Class.forName("org.postgresql.JDBC");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "admin");
            statement = connection.createStatement();
            System.out.println("подключились к БД");
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("к БД не возможно подключиться");
        }
    }
    public static void disconnect() {
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}