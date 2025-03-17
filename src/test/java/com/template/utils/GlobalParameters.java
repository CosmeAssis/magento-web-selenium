package com.template.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class GlobalParameters {
    private static Properties properties;

    static {
        loadProperties();
    }

    private static void loadProperties() {
        try {
            properties = new Properties();
            FileInputStream file = new FileInputStream("src/test/resources/config/global.properties");
            properties.load(file);
            file.close();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar o arquivo global.properties: " + e.getMessage());
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

    public static String getProperty(String key) {
        return get(key);
    }
}