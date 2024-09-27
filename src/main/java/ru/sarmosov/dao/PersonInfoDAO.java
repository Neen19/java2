package ru.sarmosov.dao;

import ru.sarmosov.model.PersonInfo;
import ru.sarmosov.util.JsonUtil;

import java.io.*;
import java.nio.file.Paths;
import java.util.Scanner;

public class PersonInfoDAO {

    static int id;
    private final File personDir;

    public PersonInfoDAO(File personDir) throws IOException {
        open();
        this.personDir = personDir;
        if (!personDir.exists()) {
            personDir.mkdirs();
        }
    }

    public PersonInfo save(PersonInfo info) throws IOException {
        File file = new File(personDir, id++ + ".json");
        JsonUtil.saveToFile(info, file);
        return info;
    }

    public PersonInfo findById(String id) throws IOException {
        File file = Paths.get(String.valueOf(personDir), id + ".json").toFile();
        if (file.exists()) {
            return JsonUtil.readValueFromFile(file, PersonInfo.class);
        } else
            throw new IOException("нет такого пользователя в бд");
    }

    private void open() throws IOException {
        File file = new File("id.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                id = scanner.nextInt();
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка чтения файла " + file);
        }
    }

    public void close() {
        try (FileWriter fileWriter = new FileWriter("id.txt")) {
            fileWriter.write(id + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete(String id) throws IOException {
        File file = new File(personDir, id + ".json");
        if (file.exists()) {
            file.delete();
        } else {
            throw new IOException("нет такого пользователя в бд");
        }
    }


    public static int getId() {
        return id;
    }

    public File getPersonDir() {
        return personDir;
    }

    public static void setId(int id) {
        PersonInfoDAO.id = id;
    }
}