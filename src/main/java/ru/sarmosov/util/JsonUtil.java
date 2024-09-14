package ru.sarmosov.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonUtil {
    public static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> void saveToFile(T object, File file) throws IOException {
        objectMapper.writeValue(file, object);
    }

    public static Object readFromFile(File file, Class<?> clazz) throws IOException {
        return objectMapper.readValue(file, clazz);
    }
}

