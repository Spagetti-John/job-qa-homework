package org.janis.qa.homework.exceptions;

public class ConfigFileNotFoundException extends RuntimeException {
    public ConfigFileNotFoundException(String fileName) {
        super("Config file: " + fileName + " not found");
    }
}
