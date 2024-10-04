package ru.sarmosov.util;

import ru.sarmosov.enums.Subject;
import ru.sarmosov.model.Person;
import ru.sarmosov.model.Student;
import ru.sarmosov.model.Teacher;

import java.util.Map;
import java.util.function.BiFunction;

public class FieldUtil {

    private final static BiFunction<Person, Object, Person> updateName = (person, obj) -> {
        person.setFullName((String) obj);
        return person;
    };

    private final static BiFunction<Person, Object, Person> updateNumber = (person, obj) -> {
        person.setPhoneNumber((String) obj);
        return person;
    };

    private final static BiFunction<Person, Object, Person> updateDate = (person, obj) -> {
        person.setBirthYear((Integer) obj);
        return person;
    };

    private final static BiFunction<Teacher, Object, Teacher> updateSubject = (person, obj) -> {
        person.setSubject(Subject.valueOf((String) obj));
        return person;
    };

    private final static BiFunction<Teacher, Object, Teacher> updateHours = (person, obj) -> {
        person.setSubject(Subject.valueOf((String) obj));
        return person;
    };

    private final static BiFunction<Student, Object, Student> updateGrades = (person, obj) -> {
        person.setAverageGrades((Map) obj);
        return person;
    };

    private static final Map<String, BiFunction<? extends Person, Object, ? extends Person>> map = Map.of(
            "fullName", updateName,
            "phoneNumber", updateNumber,
            "birthYear", updateDate,
            "subject", updateSubject,
            "workingHours", updateHours,
            "averageGrades", updateGrades
    );

    public static Person updateField(String fieldName, Person person, Object arg) {
        BiFunction func = map.get(fieldName);
        func.apply(person, arg);
        return person;
    }

}
