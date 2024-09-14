package ru.sarmosov.dao;

import ru.sarmosov.model.Person;

import java.io.IOException;

public interface DAOInterface {

    void save(Person person, String id) throws IOException;

    Person findById(String id, Class<? extends Person> clazz) throws IOException;

    void delete(String id);

}
