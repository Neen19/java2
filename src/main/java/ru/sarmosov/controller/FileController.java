package ru.sarmosov.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Queue;

public class FileController implements Runnable {
    private final File directory;
    private final Queue<String> commandQueue;

    public FileController(File directory, Queue<String> commandQueue) {
        this.directory = directory;
        this.commandQueue = commandQueue;
    }

    public Queue<String> getCommandQueue() {
        return commandQueue;
    }

    @Override
    public void run() {
        while (true) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    try {
                        String command = new String(Files.readAllBytes(file.toPath()));
                        commandQueue.add(command);
                        file.delete();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            try {
                Thread.sleep(1000);  // Проверяем раз в секунду
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
