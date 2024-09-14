package ru.sarmosov.dispatcher;

import ru.sarmosov.enums.Subject;
import ru.sarmosov.model.Teacher;
import ru.sarmosov.model.Student;
import ru.sarmosov.service.PeopleService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.Arrays;

public class Dispatcher implements Runnable {
    private final Queue<String> commandQueue;
    private final PeopleService peopleService;

    public Dispatcher(Queue<String> commandQueue, PeopleService peopleService) {
        this.commandQueue = commandQueue;
        this.peopleService = peopleService;
    }

    @Override
    public void run() {
        while (true) {
            if (!commandQueue.isEmpty()) {
                String command = commandQueue.poll();
                processCommand(command);
            }

            try {
                Thread.sleep(1000);  // Проверяем очередь раз в секунду
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Метод для обработки команд
    private void processCommand(String command) {
        String[] parts = command.split(" ");
        String action = parts[0];

        try {
            switch (action) {
                case "CREATE":
                    createPerson(parts);
                    break;
                case "UPDATE":
                    updatePerson(parts);
                    break;
                case "DELETE":
                    deletePerson(parts);
                    break;
                default:
                    System.out.println("Неизвестная команда: " + action);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Метод для создания персоны (Учитель или Ученик)
    private void createPerson(String[] parts) throws Exception {
        String type = parts[1];  // Тип: TEACHER или STUDENT
        String id = parts[2];    // Идентификатор персоны
        String fullName = parts[3].replace('_', ' ');  // ФИО
        int birthYear = Integer.parseInt(parts[4]);    // Год рождения
        String phoneNumber = parts[5];  // Номер телефона

        if ("TEACHER".equalsIgnoreCase(type)) {
            // Парсинг данных учителя
            Subject subject = Subject.valueOf(parts[6]);  // Предмет
            int workingHours = Integer.parseInt(parts[7]);  // Часы работы
            Teacher teacher = new Teacher(fullName, birthYear, phoneNumber, subject, workingHours);
            peopleService.createPerson(teacher, id);
            System.out.println("Учитель создан: " + teacher);
        } else if ("STUDENT".equalsIgnoreCase(type)) {
            // Парсинг данных ученика
            List<Subject> subjects = Arrays.stream(parts[6].split(","))
                    .map(Subject::valueOf)
                    .collect(Collectors.toList());
            Map<Subject, Double> averageGrades = new HashMap<>();
            String[] grades = parts[7].split(",");
            for (int i = 0; i < subjects.size(); i++) {
                averageGrades.put(subjects.get(i), Double.parseDouble(grades[i]));
            }
            Student student = new Student(fullName, birthYear, phoneNumber, subjects, averageGrades);
            peopleService.createPerson(student, id);
            System.out.println("Ученик создан: " + student);
        }
    }

    // Метод для обновления персоны (Учитель или Ученик)
    private void updatePerson(String[] parts) throws Exception {
        String type = parts[1];  // Тип: TEACHER или STUDENT
        String id = parts[2];    // Идентификатор персоны
        String fullName = parts[3].replace('_', ' ');  // ФИО
        int birthYear = Integer.parseInt(parts[4]);    // Год рождения
        String phoneNumber = parts[5];  // Номер телефона

        if ("TEACHER".equalsIgnoreCase(type)) {
            Subject subject = Subject.valueOf(parts[6]);
            int workingHours = Integer.parseInt(parts[7]);
            Teacher teacher = new Teacher(fullName, birthYear, phoneNumber, subject, workingHours);
            peopleService.updatePerson(id, teacher);
            System.out.println("Учитель обновлен: " + teacher);
        } else if ("STUDENT".equalsIgnoreCase(type)) {
            // Логика обновления ученика при необходимости
            // Например, обновление списка предметов и оценок
            System.out.println("Обновление ученика в текущей реализации не поддерживается.");
        }
    }

    // Метод для удаления персоны
    private void deletePerson(String[] parts) {
        String id = parts[2];  // Идентификатор персоны
        peopleService.deletePerson(id);
        System.out.println("Персона с ID " + id + " удалена.");
    }
}
