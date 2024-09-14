package ru.sarmosov;

import ru.sarmosov.dao.CachedPeopleDAO;
import ru.sarmosov.dao.ClassDAO;
import ru.sarmosov.enums.Subject;
import ru.sarmosov.model.Student;
import ru.sarmosov.model.Teacher;
import ru.sarmosov.service.PeopleService;
import ru.sarmosov.controller.FileController;
import ru.sarmosov.dispatcher.Dispatcher;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        // Создаем директорию для хранения данных
        String storagePath = "people_storage";
        File storageDir = new File(storagePath);
        if (!storageDir.exists()) {
            storageDir.mkdir();
        }

        // Инициализация DAO и PeopleService
        ClassDAO peopleDAO = new CachedPeopleDAO(new File(storagePath));
        PeopleService peopleService = new PeopleService(peopleDAO);

        // Создание примеров данных
        List<Subject> studentSubjects = new ArrayList<>();
        studentSubjects.add(Subject.MATH);
        studentSubjects.add(Subject.ENGLISH);

        Map<Subject, Double> studentGrades = new HashMap<>();
        studentGrades.put(Subject.MATH, 4.5);
        studentGrades.put(Subject.ENGLISH, 3.8);

        Student student = new Student("John Doe", 2005, "1234567890", studentSubjects, studentGrades);
        Teacher teacher = new Teacher("Jane Smith", 1980, "0987654321", Subject.MATH, 40);

        // Добавляем созданные объекты в DAO через PeopleService
        String studentId = peopleService.createPerson(student, "1");
        String teacherId = peopleService.createPerson(teacher, "2");

        // Демонстрация сохранения и извлечения данных через PeopleService
        System.out.println("Сохраненный ученик: " + peopleService.getPersonById(studentId));
        System.out.println("Сохраненный учитель: " + peopleService.getPersonById(teacherId));

        // Демонстрация удаления
        peopleService.deletePerson(studentId);
        System.out.println("Ученик удален. Попытка извлечения: " + peopleService.getPersonById(studentId));

        // Создаем контроллер для мониторинга файлов управления
        FileController controller = new FileController(new File("commands"), peopleService);
        Dispatcher dispatcher = new Dispatcher(controller.getCommandQueue(), peopleService);

        // Запуск контроллера и диспетчера в отдельных потоках
        Thread controllerThread = new Thread(controller);
        Thread dispatcherThread = new Thread(dispatcher);

        controllerThread.start();
        dispatcherThread.start();

        // Ожидание завершения работы потоков
        controllerThread.join();
        dispatcherThread.join();
    }
}
