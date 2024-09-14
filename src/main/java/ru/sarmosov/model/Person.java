package ru.sarmosov.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Person {

    protected final String fullName;
    protected final int birthYear;
    protected final String phoneNumber;
    protected static int _id = 0;
    protected int id;
    protected Class<?> clazz;

    protected Person(String phoneNumber, int birthYear, String fullName, Class<?> clazz) {
        this.phoneNumber = phoneNumber;
        this.birthYear = birthYear;
        this.fullName = fullName;
        this.clazz = clazz;
        _id++;
    }

    @Override
    public String toString() {
        return "Person{" +
                "fullName='" + fullName + '\'' +
                ", birthYear=" + birthYear +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
