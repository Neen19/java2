package ru.sarmosov.dao;

import ru.sarmosov.model.ClassInfo;
import ru.sarmosov.model.Person;
import ru.sarmosov.util.JsonUtil;

import java.io.File;
import java.io.IOException;

public class PeopleDAO {

    private final File peopleDir;
    private final ClassDAO classDAO;

    public PeopleDAO getInstance(File peopleDir, ClassDAO classDAO) {
        return new PeopleDAO(peopleDir, classDAO);
    }

    private PeopleDAO(File peopleDir, ClassDAO classDAO) {
        this.peopleDir = peopleDir;
        this.classDAO = classDAO;
        if (!peopleDir.exists()) {
            peopleDir.mkdirs();
        }
    }

    public void save(Person person) throws IOException {
        File file = new File(peopleDir, person.getId() + ".json");
        classDAO.save(new ClassInfo(person.getId(), person.getClazz()));
        JsonUtil.saveToFile(peopleDir, file);
    }

    public Person findById(String id) throws IOException {
        File peopleFile = new File(peopleDir, id + ".json");
        File classFile = classDAO.getClassDir();
        if (peopleFile.exists() && classFile.exists()) {
            ClassInfo classInfo = classDAO.findById(id);
            Object
        }
        return null;
    }

    public void delete(String id) {
        File file = new File(peopleDir, id + ".json");
        if (file.exists()) {
            file.delete();
        }
    }
}
