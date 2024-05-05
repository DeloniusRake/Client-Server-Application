package com.course_work.cs_application.services;

import com.course_work.cs_application.models.Category;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CategoryTableService implements DataBaseTableInterface<Category> {

    private static CategoryTableService INSTANCE;
    private CategoryTableService() {}
    public static CategoryTableService getInstance() {
        if (Objects.isNull(INSTANCE)) {
            INSTANCE = new CategoryTableService();
        }
        return INSTANCE;
    }

    @Override
    public Category getById(@NotNull Connection connection, int id) throws SQLException {
        String query = "SELECT * FROM categories WHERE category_id = ?";
        Category category = new Category();
        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                result.next();
                category.setCategoryID(result.getInt("category_id"));
                category.setCategoryName(result.getString("category_name"));
            }
        }
        return category;
    }

    @Override
    public List<Category> getAllRows(@NotNull Connection connection) throws SQLException {
        String query = "SELECT * FROM categories";
        List<Category> data = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    Category category = new Category();
                    category.setCategoryID(result.getInt("category_id"));
                    category.setCategoryName(result.getString("category_name"));
                    data.add(category);
                }
            }
        }
        return data;
    }

    @Override
    public void update(@NotNull Connection connection, Category category) throws SQLException {
        String query = "UPDATE categories SET category_id = ?, category_name = ? WHERE category_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, category.getCategoryID());
            statement.setString(2, category.getCategoryName());
            statement.setInt(3, category.getCategoryID());
            statement.executeUpdate();
        }
    }

    @Override
    public void deleteById(@NotNull Connection connection, int id) throws SQLException {
        String query = "DELETE FROM categories WHERE category_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.execute();
        }
    }

    @Override
    public void insert(@NotNull Connection connection, Category category) throws SQLException {
        String query = "INSERT INTO categories VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, category.getCategoryID());
            statement.setString(2, category.getCategoryName());
            statement.executeUpdate();
        }
    }
}
