package ru.sarmosov.controller;

import ru.sarmosov.model.Command;
import ru.sarmosov.util.JsonUtil;

import java.io.*;
import java.nio.file.*;
import java.util.concurrent.BlockingQueue;

public class PeopleController implements Runnable {

    private final Path folderPath;
    private final BlockingQueue<Command> queue;

    public PeopleController(String folder, BlockingQueue<Command> queue) {
        this.folderPath = Paths.get(folder);
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Files.list(folderPath).forEach(file -> {
                    try {
                        queue.put(JsonUtil.readValueFromFile(file.toFile(), Command.class));
                        Files.delete(file);
                        System.out.println("File processed and deleted: " + file);
                    } catch (IOException | InterruptedException e) {
                        System.out.println("Ошибка :" + e.getMessage());;
                    }
                });
                Thread.sleep(5000);
            } catch (IOException | InterruptedException e) {
                System.out.println("Нет команд");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    System.out.println("busy");
                }
            }
        }
    }
}
