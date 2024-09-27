package ru.sarmosov;

import ru.sarmosov.controller.PeopleController;
import ru.sarmosov.dao.CachedPeopleDAO;
import ru.sarmosov.dao.DAOInterface;
import ru.sarmosov.dao.PeopleDAO;
import ru.sarmosov.dispatcher.Dispatcher;
import ru.sarmosov.model.Command;
import ru.sarmosov.service.PeopleService;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    public static void main(String[] args) {
        BlockingQueue<Command> queue = new ArrayBlockingQueue<>(10);
        DAOInterface dao = null;
        try {
            dao = new PeopleDAO();
        } catch (Throwable e) {
            System.out.println("ошибка");
        }

        PeopleService peopleService = new PeopleService(dao);

        PeopleController controller = new PeopleController("commandsfolder", queue);
        Thread controllerThread = new Thread(controller);
        controllerThread.start();

        Dispatcher dispatcher = new Dispatcher(queue, peopleService);
        Thread dispatcherThread = new Thread(dispatcher);
        dispatcherThread.start();

    }
}