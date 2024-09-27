package ru.sarmosov.dao;

import org.mapstruct.factory.Mappers;
import ru.sarmosov.mapper.EntityMapper;
import ru.sarmosov.model.Person;
import ru.sarmosov.model.PersonInfo;
import ru.sarmosov.model.Student;
import ru.sarmosov.model.Teacher;
import ru.sarmosov.util.JsonUtil;

import static ru.sarmosov.dao.PersonInfoDAO.id;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Scanner;

public class PeopleDAO implements DAOInterface {

    private final File peopleDir = new File("person_data");
    private final PersonInfoDAO personInfoDAO;
    private final EntityMapper mapper = Mappers.getMapper(EntityMapper.class);

    public PeopleDAO() throws IOException {
        personInfoDAO = new PersonInfoDAO(new File("personinfodir"));
        if (!peopleDir.exists()) {
            peopleDir.mkdirs();
        }
    }

    public void save(Person person) throws IOException {
        File file = new File(peopleDir, id + ".json");
        PersonInfo personInfo = mapper.toEntity(person);
        setClass(personInfo, person);
        JsonUtil.saveToFile(person, file);
        personInfoDAO.save(personInfo);
    }

    public Person findById(String id) throws IOException {
        File peopleFile = new File(peopleDir, id + ".json");
        PersonInfo info = personInfoDAO.findById(id);
        if (info.isStudent()) {
            return JsonUtil.readValueFromFile(peopleFile, Student.class);
        }
        if (info.isTeacher()) {
            return JsonUtil.readValueFromFile(peopleFile, Teacher.class);
        }
        throw new IOException("Ошибка при парсинге класса");
    }

    public void delete(String id) throws IOException {
        File file = new File(peopleDir, id + ".json");
        if (file.exists()) {
            file.delete();
        } else {
            throw new IOException("нет элемента с таким id");
        }
        personInfoDAO.delete(id);
    }

    public Person update(String personId, Person person) throws IOException {
        File file = new File(peopleDir, personId + ".json");
        Field[] field = person.getClass().getDeclaredFields();
        JsonUtil.saveToFile(person, file);
        return person;
    }

    private void setClass(PersonInfo entity, Person person) {
        if (person.getClass().equals(Student.class)) {
            entity.setStudent(true);
        }
        if (person.getClass().equals(Teacher.class)) {
            entity.setTeacher(true);
        }
    }

    public void close() throws IOException {
        personInfoDAO.close();
    }
}
