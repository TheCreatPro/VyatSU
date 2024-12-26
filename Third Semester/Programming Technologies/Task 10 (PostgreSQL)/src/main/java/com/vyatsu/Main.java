package com.vyatsu;

import java.sql.*;

public class Main {
    private static Connection connection;
    private static Statement statement;

    public static void main(String[] args) {
        try {
            connect();
            createTables();  // создаём табличку
            // добавление данных
            addStudent("Иван Простой", "1234", "123456"); //1
            addStudent("Петр Первый", "5678", "789012"); //2
            //addStudent("Петр Второй", "5678", "789012");
            addStudent("Игорь Своеобразный", "1981", "798965"); //3
            //addStudent("Вася Дуров", "0001", "123123");
            addSubject("Технологии программирования"); //1
            addSubject("АРЧЗ"); //2
            addSubject("Методы оптимизации"); //3
            // студент_id, предмет_id, оценка
            addProgress(1, 1, 4);
            addProgress(1, 2, 2);
            addProgress(1, 3, 3);

            addProgress(2, 1, 4);
            addProgress(2, 2, 4);
            addProgress(2, 3, 3);

            addProgress(3, 1, 4);
            addProgress(3, 2, 5);
            addProgress(3, 3, 5);
            // вывод списка студентов, сдавших определённый предмет >3
            System.out.println("Список студентов, сдавших технологии программирования на оценку выше 3:");
            printStudentsMoreThree(1, 3);
            // средний балл по определенному предмету
            System.out.println("Средний балл по математике: " + getAverageScoreBySubject(1));
            // средний балл по определенному студенту
            System.out.println("Средний балл Ивана: " + getAverageScoreByStudent(1));
            // 3 предмета, которые сдали наибольшее кол-во студентов
            System.out.println("Три предмета, которые сдали наибольшее количество студентов:");
            printThreeSubjects();

            System.out.println("Студенты на стипуху:");
            //test();

            stipuha();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    private static void connect() throws SQLException {
        try {
            //Class.forName("org.postgresql.JDBC");
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "admin");
            statement = connection.createStatement();
            System.out.println("подключились к БД");
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("к БД не возможно подключиться");
        }
    }

    private static void disconnect() {
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

    private static void createTables() throws SQLException {
        statement.execute("DROP TABLE IF EXISTS успеваемость");
        statement.execute("DROP TABLE IF EXISTS предметы");
        statement.execute("DROP TABLE IF EXISTS студенты");

        statement.execute("CREATE TABLE студенты (" +
                "id SERIAL PRIMARY KEY, " +
                "имя TEXT, " +
                "серия_паспорта TEXT, " +
                "номер_паспорта TEXT, " +
                "UNIQUE (серия_паспорта, номер_паспорта))");

        statement.execute("CREATE TABLE предметы (" +
                "id SERIAL PRIMARY KEY, " +
                "название TEXT)");

        statement.execute("CREATE TABLE успеваемость (" +
                "id SERIAL PRIMARY KEY, " +
                "студент_id INTEGER, " +
                "предмет_id INTEGER, " +
                "оценка INTEGER, " +
                "FOREIGN KEY (студент_id) REFERENCES студенты(id) ON DELETE CASCADE, " +
                "FOREIGN KEY (предмет_id) REFERENCES предметы(id) ON DELETE CASCADE)");
    }

    private static void addStudent(String name, String passportSeries, String passportNumber) throws SQLException {
        statement.execute("INSERT INTO студенты (имя, серия_паспорта, номер_паспорта) VALUES ('" + name + "', '" + passportSeries + "', '" + passportNumber + "')");
    }

    private static void addSubject(String name) throws SQLException {
        statement.execute("INSERT INTO предметы (название) VALUES ('" + name + "')");
    }

    // добавление успеваемости
    private static void addProgress(int studentId, int subjectId, int score) throws SQLException {
        statement.execute("INSERT INTO успеваемость (студент_id, предмет_id, оценка) VALUES (" + studentId + ", " + subjectId + ", " + score + ")");
    }

    // вывод списка студентов, сдавших определённый предмет > score
    private static void printStudentsMoreThree(int subjectId, int score) throws SQLException {
        ResultSet rs = statement.executeQuery("SELECT s.имя FROM студенты AS s JOIN успеваемость AS u ON s.id = u.студент_id WHERE u.предмет_id = " + subjectId + " AND u.оценка > " + score);
        while (rs.next()) {
            System.out.println(rs.getString(1));
        }
    }

    // средний балл по определенному предмету
    private static double getAverageScoreBySubject(int subjectId) throws SQLException {
        ResultSet rs = statement.executeQuery("SELECT AVG(оценка) FROM успеваемость WHERE предмет_id = " + subjectId);
        rs.next();
        return rs.getDouble(1);
    }

    // средний балл по определенному студенту
    private static double getAverageScoreByStudent(int studentId) throws SQLException {
        ResultSet rs = statement.executeQuery("SELECT AVG(оценка) FROM успеваемость WHERE студент_id = " + studentId);
        rs.next();
        return rs.getDouble(1);
    }

    // 3 предмета, которые сдали наибольшее кол-во студентов
    private static void printThreeSubjects() throws SQLException {
        ResultSet rs = statement.executeQuery("SELECT p.название, COUNT(*) FROM предметы AS p JOIN успеваемость AS u ON p.id = u.предмет_id GROUP BY p.название ORDER BY COUNT(*) DESC LIMIT 3");
        while (rs.next()) {
            System.out.println(rs.getString(1) + " - " + rs.getInt(2));
        }
    }

    // вывести всех кто идет на стипендию
    private static void stipuha() throws SQLException {
        String query = "SELECT s.имя FROM студенты AS s " +
                "WHERE NOT EXISTS (" +
                "    SELECT 1 FROM предметы AS sub " +
                "    LEFT JOIN успеваемость AS u ON sub.id = u.предмет_id AND u.студент_id = s.id " +
                "    WHERE u.оценка <= 3 OR u.оценка IS NULL" +
                ")";
        try (ResultSet rs = statement.executeQuery(query)) {
            while (rs.next()) {
                System.out.println(rs.getString("имя"));
            }
        }
    }

}