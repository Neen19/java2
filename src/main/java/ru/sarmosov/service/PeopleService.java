package ru.sarmosov.service;

import ru.sarmosov.dao.DAOInterface;
import ru.sarmosov.enums.Subject;
import ru.sarmosov.model.Person;
import ru.sarmosov.model.Student;
import ru.sarmosov.model.Teacher;
import ru.sarmosov.util.ReflectUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.NoSuchFileException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PeopleService {

    private final List<String> studentFields;
    private final List<String> teacherFields;
    private final DAOInterface dao;

    public PeopleService(DAOInterface dao) {
        this.dao = dao;
        final List<String> personFields = Arrays.stream(Person.class.getDeclaredFields()).map(Field::getName).toList();
        Stream<String> studentFieldsStream = Arrays.stream(Student.class.getDeclaredFields()).map(Field::getName);
        Stream<String> teacherFieldsStream = Arrays.stream(Teacher.class.getDeclaredFields()).map(Field::getName);
        teacherFields = Stream.concat(personFields.stream(), teacherFieldsStream).toList();
        studentFields = Stream.concat(personFields.stream(), studentFieldsStream).toList();
    }

    public Person cretePerson(Person person) throws IOException {
        dao.save(person);
        return person;
    }

    public Person updatePersonField(String id, Object arg, String fieldName) throws IOException, IllegalAccessException, NoSuchFieldException {
        Person person = dao.findById(id);
        if (!isFieldCorrect(person.getClass(), fieldName)) {
            throw new IOException("нет такого поля");
        }
        Field field;
        try {
             field = person.getClass().getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            field = person.getClass().getSuperclass().getDeclaredField(fieldName);
        }
        Class<?> clazz = field.getType();
        if (clazz.isEnum()) {
            arg = Enum.valueOf((Class<Enum>) clazz, (String) arg);
        }
        if (Map.class.isAssignableFrom(clazz)) {
            ((Map<String, ?>) arg).keySet().stream()
                    .map(Subject::valueOf);
        }
        ReflectUtils.cast(arg, clazz);
        field.setAccessible(true);
        field.set(person, arg);
        return dao.update(id, person);
    }

    public void deletePerson(String id) throws IOException {
        dao.delete(id);
    }

    public Person getPerson(String id) throws IOException {
        return dao.findById(id);
    }

    private boolean isFieldCorrect(Class<? extends Person> personClass, String fieldName) {
        System.out.println(fieldName);
        if (personClass.equals(Teacher.class)) {
            return isInList(teacherFields, fieldName);
        } else {
            return isInList(studentFields, fieldName);
        }
    }

    private <T> boolean isInList(List<T> list, T elem) {
        return list.contains(elem);
    }
}