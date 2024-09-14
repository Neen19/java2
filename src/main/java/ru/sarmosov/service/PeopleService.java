package ru.sarmosov.service;

import ru.sarmosov.dao.ClassDAO;
import ru.sarmosov.model.Person;

import java.io.IOException;

public class PeopleService {
    private final ClassDAO dao;

    public PeopleService(ClassDAO dao) {
        this.dao = dao;
    }

    public String createPerson(Person person, String id) throws IOException {
        return dao.save(person, id);
    }

    public Person getPersonById(String id, Class<? extends Person> clazz) throws IOException {
        return dao.findById(id, clazz);
    }

    public void updatePerson(String id, Person person) throws IOException {
        dao.save(person, id);
    }

    public void deletePerson(String id) {
        dao.delete(id);
    }
}

