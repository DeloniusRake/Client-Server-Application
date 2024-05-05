package com.course_work.cs_application.services;

import com.course_work.cs_application.models.Product;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductTableService implements DataBaseTableInterface<Product> {

    private static ProductTableService INSTANCE;
    private ProductTableService() {}
    public static ProductTableService getInstance() {
        if (Objects.isNull(INSTANCE)) {
            INSTANCE = new ProductTableService();
        }
        return INSTANCE;
    }

    @Override
    public Product getById(@NotNull Connection connection, int id) throws SQLException {
        String query = "SELECT * FROM products WHERE product_id = ?";
        Product product = new Product();
        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                result.next();
                product.setProductID(result.getInt("product_id"));
                product.setProductName(result.getString("product_name"));
                product.setCategoryID(result.getInt("category_id"));
                product.setManufacturerID(result.getInt("manufacturer_id"));
                product.setImg(result.getString("img"));
                product.setDescription(result.getString("description"));
                product.setPrice(result.getDouble("price"));
                product.setWeight(result.getDouble("weight"));
            }
        }
        return product;
    }

    @Override
    public List<Product> getAllRows(@NotNull Connection connection) throws SQLException {
        String query = "SELECT * FROM products";
        List<Product> data = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    Product product = new Product();
                    product.setProductID(result.getInt("product_id"));
                    product.setProductName(result.getString("product_name"));
                    product.setCategoryID(result.getInt("category_id"));
                    product.setManufacturerID(result.getInt("manufacturer_id"));
                    product.setImg(result.getString("img"));
                    product.setDescription(result.getString("description"));
                    product.setPrice(result.getDouble("price"));
                    product.setWeight(result.getDouble("weight"));
                    data.add(product);
                }
            }
        }
        return data;
    }

    @Override
    public void update(@NotNull Connection connection, Product product) throws SQLException {
        String query = "UPDATE products SET product_id = ?, product_name = ?, category_id = ?, manufacturer_id = ?, img = ?, description = ?, price = ?, weight = ? WHERE category_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, product.getProductID());
            statement.setString(2, product.getProductName());
            statement.setInt(3, product.getCategoryID());
            statement.setInt(4, product.getManufacturerID());
            statement.setString(5, product.getImg());
            statement.setString(6, product.getDescription());
            statement.setDouble(7, product.getPrice());
            statement.setDouble(8, product.getWeight());
            statement.setInt(9, product.getProductID());
            statement.executeUpdate();
        }
    }

    @Override
    public void deleteById(@NotNull Connection connection, int id) throws SQLException {
        String query = "DELETE FROM products WHERE product_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.execute();
        }
    }

    @Override
    public void insert(@NotNull Connection connection, Product product) throws SQLException {
        String query = "INSERT INTO products VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, product.getProductID());
            statement.setInt(2, product.getCategoryID());
            statement.setInt(3, product.getManufacturerID());
            statement.setString(4, product.getProductName());
            statement.setString(5, product.getImg());
            statement.setString(6, product.getDescription());
            statement.setDouble(7, product.getPrice());
            statement.setDouble(8, product.getWeight());
            statement.executeUpdate();
        }
    }

    public List<Product> getByCategoryID(@NotNull Connection connection, int id) throws SQLException {
        String query = "SELECT * FROM products WHERE category_id = ?";
        List<Product> data = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    Product product = new Product();
                    product.setProductID(result.getInt("product_id"));
                    product.setProductName(result.getString("product_name"));
                    product.setCategoryID(result.getInt("category_id"));
                    product.setManufacturerID(result.getInt("manufacturer_id"));
                    product.setImg(result.getString("img"));
                    product.setDescription(result.getString("description"));
                    product.setPrice(result.getDouble("price"));
                    product.setWeight(result.getDouble("weight"));
                    data.add(product);
                }
            }
        }
        return data;
    }

    public List<Product> getByManufacturerID(@NotNull Connection connection, int id) throws SQLException {
        String query = "SELECT * FROM products WHERE manufacturer_id = ?";
        List<Product> data = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    Product product = new Product();
                    product.setProductID(result.getInt("product_id"));
                    product.setProductName(result.getString("product_name"));
                    product.setCategoryID(result.getInt("category_id"));
                    product.setManufacturerID(result.getInt("manufacturer_id"));
                    product.setImg(result.getString("img"));
                    product.setDescription(result.getString("description"));
                    product.setPrice(result.getDouble("price"));
                    product.setWeight(result.getDouble("weight"));
                    data.add(product);
                }
            }
        }
        return data;
    }

    public List<Product> getBySearchQuery(@NotNull Connection connection, String searchQuery) throws SQLException {
        searchQuery = "'%".concat(searchQuery).concat("%'");
        String query = "SELECT * FROM products WHERE product_name ILIKE ".concat(searchQuery);
        List<Product> data = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    Product product = new Product();
                    product.setProductID(result.getInt("product_id"));
                    product.setProductName(result.getString("product_name"));
                    product.setCategoryID(result.getInt("category_id"));
                    product.setManufacturerID(result.getInt("manufacturer_id"));
                    product.setImg(result.getString("img"));
                    product.setDescription(result.getString("description"));
                    product.setPrice(result.getDouble("price"));
                    product.setWeight(result.getDouble("weight"));
                    data.add(product);
                }
            }
        }
        return data;
    }
}
