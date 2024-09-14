package ru.sarmosov.dao;

import lombok.Getter;
import ru.sarmosov.model.ClassInfo;

import java.io.File;
import java.io.IOException;

import static ru.sarmosov.util.JsonUtil.objectMapper;

@Getter
public class ClassDAO {

    public static ClassDAO getInstance(File classDir) {
        return new ClassDAO(classDir);
    }

    private final File classDir;

    private ClassDAO(File db) {
        this.classDir = db;
        if (!db.exists()) {
            db.mkdirs();
        }
    }

    public ClassInfo save(ClassInfo info) throws IOException {
        File file = new File(classDir, info.getId() + ".json");
        objectMapper.writeValue(file, info);
        return info;
    }

    public ClassInfo findById(String id) throws IOException {
        File file = new File(classDir, id + ".json");
        if (file.exists()) {
            return objectMapper.readValue(file, ClassInfo.class);
        }
        throw new RuntimeException();
    }

    public void delete(String id) {
        File file = new File(classDir, id + ".json");
        if (file.exists()) {
            file.delete();
        }
    }
}
