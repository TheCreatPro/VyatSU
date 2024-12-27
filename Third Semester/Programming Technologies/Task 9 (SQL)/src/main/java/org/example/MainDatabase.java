package org.example;

import java.sql.*;
// для maven: https://mvnrepository.com/artifact/org.xerial/sqlite-jdbc/3.47.0.0
public class MainDatabase {

    private static Connection connection;  // Подключение к бд
    private static Statement statement; // Для запросов к бд

    public static void main(String[] args) {
        try {
            connect();
            statement.executeUpdate("UPDATE user SET age = 26 WHERE id = 1");  // обновляем элемент в бд
            statement.executeUpdate("INSERT INTO user (name, age) VALUES ('Bob3', 50)");
            ResultSet rs = statement.executeQuery("SELECT * FROM user"); // достать всё
            while (rs.next()) {
                System.out.println(rs.getInt("id") + rs.getString("name") + rs.getInt("age"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void connect() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/TheCreatPro/OneDrive - vyatsu/Учёба/Programming/IntelliJ IDEA/Task_9/src/main/java/org/example/database/database_example");
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("БД не возможно подключиться");
        }
    }
}
