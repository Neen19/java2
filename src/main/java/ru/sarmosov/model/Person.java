package ru.sarmosov.model;

public abstract class Person {

    protected final String fullName;
    protected final int birthYear;
    protected final String phoneNumber;

    protected Person(String phoneNumber, int birthYear, String fullName) {
        this.phoneNumber = phoneNumber;
        this.birthYear = birthYear;
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "fullName='" + fullName + '\'' +
                ", birthYear=" + birthYear +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    public String getFullName() {
        return fullName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}