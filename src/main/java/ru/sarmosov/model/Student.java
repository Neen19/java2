package ru.sarmosov.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.sarmosov.enums.Subject;
import ru.sarmosov.util.SubjectKeyDeserializer;
import ru.sarmosov.util.SubjectKeySerializer;

import java.util.LinkedHashMap;
import java.util.Map;

public class Student extends Person {

    @JsonSerialize(keyUsing = SubjectKeySerializer.class)
    @JsonDeserialize(keyUsing = SubjectKeyDeserializer.class)
    private Map<Subject, Double> averageGrades;

    public Student() {
        super(null, 0, null);
        averageGrades = new LinkedHashMap<>();
    }

    public Student(String fullName, int birthYear, String phoneNumber, Map<Subject, Double> averageGrades) {
        super(fullName, birthYear, phoneNumber);
        this.averageGrades = averageGrades;
    }

    public Student(Student other) {
        super(other.fullName, other.birthYear, other.phoneNumber);
        this.averageGrades = other.averageGrades;

    }

    @Override
    public String toString() {
        return "Student{" +
                "fullName='" + fullName + '\'' +
                ", birthYear=" + birthYear +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", averageGrades=" + averageGrades +
                '}';
    }

    public Map<Subject, Double> getAverageGrades() {
        return averageGrades;
    }

    public void setAverageGrades(Map<Subject, Double> averageGrades) {
        this.averageGrades = averageGrades;
    }
}