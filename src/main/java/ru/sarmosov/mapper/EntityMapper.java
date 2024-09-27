package ru.sarmosov.mapper;

import org.mapstruct.Mapper;
import ru.sarmosov.model.Person;
import ru.sarmosov.model.PersonInfo;

@Mapper
public interface EntityMapper {

    PersonInfo toEntity(Person person);

}
