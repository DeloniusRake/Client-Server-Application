package com.course_work.cs_application.utilits;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class ApplicationProperties {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationProperties.class);

    private static final String FILE_NAME = "/application.properties";

    private static ApplicationProperties INSTANCE;

    private ApplicationProperties() {}

    private final Properties properties = new Properties();

    private void init() {
        try (InputStream is = getClass().getResourceAsStream(FILE_NAME)) {
            if (Objects.nonNull(is)) properties.load(is);
        }
        catch (IOException exception) {
            LOGGER.info(exception.getMessage(), exception);
        }
    }

    public Properties getProperties() {return properties;}

    public static ApplicationProperties getInstance() {
        if (Objects.isNull(INSTANCE)) {
            INSTANCE = new ApplicationProperties();
            INSTANCE.init();
        }

        return INSTANCE;
    }

}