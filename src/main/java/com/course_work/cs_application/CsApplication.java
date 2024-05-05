package com.course_work.cs_application;

import com.course_work.cs_application.controllers.ProductPageController;
import com.course_work.cs_application.utilits.DBProperties;
import com.course_work.cs_application.utilits.DataBaseInit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class CsApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductPageController.class);

    public static void main(String[] args) {

        DBProperties properties = DBProperties.getProperties();
        try (Connection connection = DriverManager.getConnection(
                properties.getUrl(),
                properties.getUser(),
                properties.getPassword()
        )) {
            DataBaseInit.getInstance().init(connection);
        }
        catch (SQLException exception) {
            LOGGER.info(exception.getMessage(), exception);
        }
        SpringApplication.run(CsApplication.class, args);
    }

}
