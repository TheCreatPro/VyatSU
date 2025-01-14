package com.vyatsu.task14.repositories;

import com.vyatsu.task14.entities.Product;
import com.vyatsu.task14.config.DatabaseConfig;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {


    // выполняет запрос на поиск продукта по ид. если продукт найден, возвращает Product, иначе null
    public Product findById(Long id) {
        try (Connection connection = DatabaseConfig.getConnection();
             Statement statement = connection.createStatement()) {

            String query = "SELECT * FROM products WHERE id = " + id;
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getLong("id"));
                product.setTitle(resultSet.getString("title"));
                product.setPrice(resultSet.getInt("price"));
                return product;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    // Получает все записи из таблицы products и преобразует их в список объектов Product
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = DatabaseConfig.getConnection();
             Statement statement = connection.createStatement()) {

            String query = "SELECT * FROM products";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getLong("id"));
                product.setTitle(resultSet.getString("title"));
                product.setPrice(resultSet.getInt("price"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    // Чекает, существует ли запись с таким ид. Если существует, обновляет, нет - добавляет новую
    public void save(Product product) {
        try (Connection connection = DatabaseConfig.getConnection();
             Statement statement = connection.createStatement()) {

            // Проверка на существование продукта с таким ID
            String checkQuery = "SELECT COUNT(*) AS count FROM products WHERE id = " + product.getId();
            ResultSet resultSet = statement.executeQuery(checkQuery);
            resultSet.next();
            int count = resultSet.getInt("count");

            if (count > 0) {
                // Обновление существующего продукта
                String updateQuery = "UPDATE products SET title = '" + product.getTitle() +
                        "', price = " + product.getPrice() +
                        " WHERE id = " + product.getId();
                statement.executeUpdate(updateQuery);
            } else {
                // Добавление нового продукта
                String insertQuery = "INSERT INTO products (id, title, price) VALUES (" +
                        product.getId() + ", '" +
                        product.getTitle() + "', " +
                        product.getPrice() + ")";
                statement.executeUpdate(insertQuery);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Удаляет запись с указанным ид
    public void deleteById(Long id) {
        try (Connection connection = DatabaseConfig.getConnection();
             Statement statement = connection.createStatement()) {

            String query = "DELETE FROM products WHERE id = " + id;
            statement.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
