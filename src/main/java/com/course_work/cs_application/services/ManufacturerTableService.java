package com.course_work.cs_application.services;

import com.course_work.cs_application.models.Manufacturer;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ManufacturerTableService implements DataBaseTableInterface<Manufacturer> {

    private static ManufacturerTableService INSTANCE;
    private ManufacturerTableService() {}
    public static ManufacturerTableService getInstance() {
        if (Objects.isNull(INSTANCE)) {
            INSTANCE = new ManufacturerTableService();
        }
        return INSTANCE;
    }

    @Override
    public Manufacturer getById(@NotNull Connection connection, int id) throws SQLException {
        String query = "SELECT * FROM manufactures WHERE manufacturer_id = ?";
        Manufacturer manufacturer = new Manufacturer();
        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                result.next();
                manufacturer.setManufacturerID(result.getInt("manufacturer_id"));
                manufacturer.setManufacturerName(result.getString("manufacturer_name"));
            }
        }
        return manufacturer;
    }

    @Override
    public List<Manufacturer> getAllRows(@NotNull Connection connection) throws SQLException {
        String query = "SELECT * FROM manufactures";
        List<Manufacturer> data = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    Manufacturer manufacturer = new Manufacturer();
                    manufacturer.setManufacturerID(result.getInt("manufacturer_id"));
                    manufacturer.setManufacturerName(result.getString("manufacturer_name"));
                    data.add(manufacturer);
                }
            }
        }
        return data;
    }

    @Override
    public void update(@NotNull Connection connection, Manufacturer manufacturer) throws SQLException {
        String query = "UPDATE manufactures SET manufacturer_id = ?, manufacturer_name = ? WHERE manufacturer_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, manufacturer.getManufacturerID());
            statement.setString(2, manufacturer.getManufacturerName());
            statement.setInt(3, manufacturer.getManufacturerID());
            statement.executeUpdate();
        }
    }

    @Override
    public void deleteById(@NotNull Connection connection, int id) throws SQLException {
        String query = "DELETE FROM manufactures WHERE manufacturer_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.execute();
        }
    }

    @Override
    public void insert(@NotNull Connection connection, Manufacturer manufacturer) throws SQLException {
        String query = "INSERT INTO manufactures VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, manufacturer.getManufacturerID());
            statement.setString(2, manufacturer.getManufacturerName());
            statement.executeUpdate();
        }
    }

}
