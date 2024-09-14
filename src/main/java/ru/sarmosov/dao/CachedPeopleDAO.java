package ru.sarmosov.dao;

import ru.sarmosov.model.Person;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CachedPeopleDAO extends ClassDAO {
    private final Map<String, Person> cache = new HashMap<>();

    public CachedPeopleDAO(File directory) {
        super(directory);
    }

    @Override
    public void save(Person person, String id) throws IOException {
        super.save(person, id);
        cache.put(id, person);
    }

    @Override
    public Person findById(String id) throws IOException {
        if (cache.containsKey(id)) {
            return cache.get(id);
        }
        return super.findById(id, );
    }

    @Override
    public void delete(String id) {
        super.delete(id);
        cache.remove(id);
    }
}
