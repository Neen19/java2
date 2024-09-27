package ru.sarmosov.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.sarmosov.enums.Subject;
import ru.sarmosov.util.SubjectKeyDeserializer;
import ru.sarmosov.util.SubjectKeySerializer;

public class Teacher extends Person {

    @JsonSerialize(keyUsing = SubjectKeySerializer.class)
    @JsonDeserialize(keyUsing = SubjectKeyDeserializer.class)
    private Subject subject;
    private int workingHours;

    public Teacher() {
        super(null, 0, null);
    }

    public Teacher(String fullName, int birthYear, String phoneNumber, Subject subject, int workingHours) {
        super(fullName, birthYear, phoneNumber);
        this.subject = subject;
        this.workingHours = workingHours;
    }

    public Teacher(Teacher other) {
        super(other.fullName, other.birthYear, other.phoneNumber);
        this.subject = other.subject;
        this.workingHours = other.workingHours;

    }

    @Override
    public String toString() {
        return "Teacher{" +
                super.toString() +
                ", subject=" + subject +
                ", workingHours=" + workingHours +
                '}';
    }

    public Subject getSubject() {
        return subject;
    }

    public int getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(int workingHours) {
        this.workingHours = workingHours;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
