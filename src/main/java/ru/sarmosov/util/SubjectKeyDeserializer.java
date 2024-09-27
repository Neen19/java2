package ru.sarmosov.util;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import ru.sarmosov.enums.Subject;

import java.io.IOException;

public class SubjectKeyDeserializer extends KeyDeserializer {
    @Override
    public Object deserializeKey(String key, DeserializationContext ctxt) throws IOException {
        return Subject.valueOf(key.toUpperCase());
    }
}
