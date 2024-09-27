package ru.sarmosov.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;

import java.io.IOException;

public class SubjectKeySerializer extends JsonSerializer<Object> {
    @Override
    public void serialize(Object key, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeFieldName(key.toString().toUpperCase());
    }
}
