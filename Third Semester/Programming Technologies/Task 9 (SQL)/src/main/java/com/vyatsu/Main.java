package com.vyatsu;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.*;

public class Main {
    private static Connection connection;
    private static Statement statement;

    public static void main(String[] args) {
        Losos losos1 = new Losos(1, "Losos1", 5, 107.5F, maxSwimDist.LONG);
        Losos losos2 = new Losos(2, "Losos2", 17, 50.2F, maxSwimDist.MEDIUM);
        Losos losos3 = new Losos(3, "Losos3", 6, 32.3F, maxSwimDist.SHORT);

        try {
            connect();
            createTable(Losos.class);

            insertIntoTable(losos1);
            insertIntoTable(losos2);
            insertIntoTable(losos3);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    private static void connect() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\TheCreatPro\\OneDrive - vyatsu\\Учёба\\Programming\\IntelliJ IDEA\\Task_9\\src\\main\\java\\com\\vyatsu\\losos_database");
            statement = connection.createStatement();
            System.out.println("Подключились к БД");
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Не удалось подключиться к БД", e);
        }
    }

    private static void disconnect() {
        try {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
            System.out.println("Отключились от БД");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTable(Class<?> input_class) throws SQLException {
//        if
        Table table = input_class.getAnnotation(Table.class);
        statement.execute("DROP TABLE IF EXISTS " + table.title());

        String createQuery = "CREATE TABLE IF NOT EXISTS " + table.title() + " (";

        Field[] fields = input_class.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                createQuery += field.getName() + " ";

                System.out.println(field.getType());
                if (field.getType() == int.class) {
                    createQuery += "INTEGER";
                } else if (field.getType() == float.class) {
                    createQuery += "NUMERIC";
                } else if (field.getType() == String.class) {
                    createQuery += "TEXT";
                } else if (field.getType().isEnum()) {
                    createQuery += "TEXT";
                }
                createQuery += ", ";
            }
        }

        createQuery = createQuery.substring(0, createQuery.length() - 2) + ");";

        System.out.println("Создание таблицы: " + createQuery);
        statement.execute(createQuery);
    }

    private static void insertIntoTable(Object obj) throws SQLException, IllegalAccessException {
        Class<?> input_class = obj.getClass();

//        if

        Table table = input_class.getAnnotation(Table.class);
        String insertQuery = "INSERT INTO " + table.title() + " (";
        String valuesPart = "VALUES (";

        Field[] fields = input_class.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                field.setAccessible(true);

                insertQuery += field.getName() + ", ";

                Object value = field.get(obj);
                if (value instanceof String || value instanceof Enum) {
                    valuesPart += "'" + value + "', ";
                } else {
                    valuesPart += value + ", ";
                }
            }
        }

        insertQuery = insertQuery.substring(0, insertQuery.length() - 2) + ") ";
        valuesPart = valuesPart.substring(0, valuesPart.length() - 2) + ");";

        String finalQuery = insertQuery + valuesPart;
        System.out.println("Вставка данных: " + finalQuery);
        statement.execute(finalQuery);
    }
}