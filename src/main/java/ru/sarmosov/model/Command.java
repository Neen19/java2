package ru.sarmosov.model;


public class Command {

    private String id;

    private String command;

    private boolean isStudent;

    private boolean isTeacher;

    private Student studentObj;

    private Teacher teacherObj;

    private String fieldName;

    private Object arg;

    public String getId() {
        return id;
    }

    public String getCommand() {
        return command;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public boolean isTeacher() {
        return isTeacher;
    }

    public Student getStudentObj() {
        return studentObj;
    }

    public Teacher getTeacherObj() {
        return teacherObj;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getArg() {
        return arg;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void setStudent(boolean student) {
        isStudent = student;
    }

    public void setTeacher(boolean teacher) {
        isTeacher = teacher;
    }

    public void setStudentObj(Student studentObj) {
        this.studentObj = studentObj;
    }

    public void setTeacherObj(Teacher teacherObj) {
        this.teacherObj = teacherObj;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public void setArg(Object arg) {
        this.arg = arg;
    }
}
