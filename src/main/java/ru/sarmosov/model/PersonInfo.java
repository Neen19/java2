package ru.sarmosov.model;

public class PersonInfo {

    private boolean isStudent;
    private boolean isTeacher;

    public boolean isStudent() {
        return isStudent;
    }

    public boolean isTeacher() {
        return isTeacher;
    }

    public void setStudent(boolean student) {
        isStudent = student;
    }

    public void setTeacher(boolean teacher) {
        isTeacher = teacher;
    }

    @Override
    public String toString() {
        return "PersonInfo{" +
                "isStudent=" + isStudent +
                ", isTeacher=" + isTeacher +
                '}';
    }
}