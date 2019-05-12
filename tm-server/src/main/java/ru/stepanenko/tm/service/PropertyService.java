package ru.stepanenko.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.stepanenko.tm.AppServer;
import ru.stepanenko.tm.api.service.IPropertyService;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyService implements IPropertyService {
    @NotNull private final Properties properties = new Properties();
    public PropertyService() {
        try (InputStream resourceStream = AppServer.class.getClassLoader().getResourceAsStream("application.properties")) {
            properties.load(resourceStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getJdbcPassword() {
        return properties.getProperty("password");
    }

    @Override
    public String getJdbcUser() {
        return properties.getProperty("user");
    }

    @Override
    public String getJdbcURL() {
        return properties.getProperty("url");
    }

    @Override
    public String getJdbcDriver() {
        return properties.getProperty("driver");
    }

    @Override
    public String getSalt() {
        return properties.getProperty("salt");
    }

    @Override
    public String getCycle() {
        return properties.getProperty("cycle");
    }
}