package ru.sarmosov.dao;

import ru.sarmosov.model.Person;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static ru.sarmosov.dao.PersonInfoDAO.id;

public class CachedPeopleDAO extends PeopleDAO {

    private final Map<String, Person> cache = new HashMap<>();

    public CachedPeopleDAO() throws IOException {
        super();
    }

    @Override
    public void save(Person person) throws IOException {
        super.save(person);
        System.out.println("put to cache");
        cache.put(String.valueOf(id), person);
    }

    @Override
    public Person findById(String id) throws IOException {
        if (cache.containsKey(id)) {
            System.out.println("get from cache");
            return cache.get(id);
        }
        Person person = super.findById(String.valueOf(id));
        cache.put(String.valueOf(id), person);
        return person;
    }

    @Override
    public void delete(String id) throws IOException {
        super.delete(String.valueOf(id));
        System.out.println("remove from cache");
        cache.remove(id);
    }
}
