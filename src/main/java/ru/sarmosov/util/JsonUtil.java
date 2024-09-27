package ru.sarmosov.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import ru.sarmosov.enums.Subject;

import java.io.File;
import java.io.IOException;

public class JsonUtil {
    public static final ObjectMapper objectMapper = new ObjectMapper();
    static {
        SimpleModule module = new SimpleModule();
        module.addKeySerializer(Subject.class, new SubjectKeySerializer());
        module.addKeyDeserializer(Subject.class, new SubjectKeyDeserializer());
        objectMapper.registerModule(module);
    }

    public static <T> void saveToFile(T object, File file) throws IOException {
        objectMapper.writeValue(file, object);
    }

    public static  <T> T readValueFromFile(File file, Class<T> clazz) throws IOException {
        return objectMapper.readValue(file, clazz);
    }
}

