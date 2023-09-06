package org.janis.qa.homework.config;

import org.janis.qa.homework.exceptions.ConfigFileNotFoundException;

import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

public class Config {
    private static final String BASE_URL = "base.url";
    private static final String USERS_CSV_FILE = "users.csv.file";

    private static Config INSTANCE;

    private final Properties properties;

    private Config() {
        var fileName = Optional.ofNullable(System.getProperty("env")).orElse("prod") + ".properties";
        var configInputStream = getClass().getClassLoader().getResourceAsStream(fileName);

        properties = new Properties();

        Optional.ofNullable(configInputStream).ifPresentOrElse(
            inputStream -> {
                try {
                    properties.load(inputStream);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            },
            () -> {
                throw new ConfigFileNotFoundException(fileName);
            });
    }

    public static Config getInstance() {
        // In case multiple threads try to read the same config file
        synchronized (Config.class) {
            return Optional.ofNullable(INSTANCE).orElseGet(() -> {
                INSTANCE = new Config();
                return INSTANCE;
            });
        }
    }

    public String getBaseUrl() {
        return properties.getProperty(BASE_URL);
    }

    public String getUsersCsvFile() {
        return properties.getProperty(USERS_CSV_FILE);
    }
}
