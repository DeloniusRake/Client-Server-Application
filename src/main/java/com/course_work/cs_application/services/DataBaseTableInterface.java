package com.course_work.cs_application.services;

import org.springframework.lang.NonNull;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DataBaseTableInterface<T> {

    T getById(@NonNull Connection connection, int id) throws SQLException;
    List<T> getAllRows(@NonNull Connection connection) throws SQLException;
    void update(@NonNull Connection connection, T modifiedObject) throws SQLException;
    void deleteById(@NonNull Connection connection, int id) throws SQLException;
    void insert(@NonNull Connection connection, T newObject) throws SQLException;

}
