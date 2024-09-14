package ru.sarmosov.model;

import lombok.Getter;
import lombok.Setter;
import ru.sarmosov.enums.Subject;

@Getter
@Setter
public class Teacher extends Person {

    private final Subject subject;
    private final int workingHours;

    public Teacher(String fullName, int birthYear, String phoneNumber, Subject subject, int workingHours) {
        super(fullName, birthYear, phoneNumber);
        this.subject = subject;
        this.workingHours = workingHours;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                super.toString() +
                ", subject=" + subject +
                ", workingHours=" + workingHours +
                '}';
    }
}
