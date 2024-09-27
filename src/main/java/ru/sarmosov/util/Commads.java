package ru.sarmosov.util;

import ru.sarmosov.model.Command;
import ru.sarmosov.model.Person;
import ru.sarmosov.model.Student;
import ru.sarmosov.model.Teacher;

public class Commads {

    public static Person createPersonFromCommand(Command command) {
        Person person = null;
        if (command.isTeacher()) {
            person = new Teacher(command.getTeacherObj());
        } else {
            person = new Student(command.getStudentObj());
        }
        return person;
    }

}
