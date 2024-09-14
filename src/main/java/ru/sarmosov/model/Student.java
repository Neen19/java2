package ru.sarmosov.model;

import lombok.Getter;
import lombok.Setter;
import ru.sarmosov.enums.Subject;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
public class Student extends Person {

    private final Map<Subject, Double> averageGrades;

    public Student(String fullName, int birthYear, String phoneNumber, List<Subject> subjects) {
        super(fullName, birthYear, phoneNumber);
        this.averageGrades = subjects.stream()
                .collect(Collectors.toMap(subject -> subject, _ -> 0.0));
    }

}
