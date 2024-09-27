package ru.sarmosov.dao;

import ru.sarmosov.model.Person;

import java.io.IOException;

public interface DAOInterface {

    void save(Person person) throws IOException;

    Person findById(String id) throws IOException;

    void delete(String id) throws IOException;

    Person update(String id, Person person) throws IOException;

}
