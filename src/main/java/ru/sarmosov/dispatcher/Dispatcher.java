package ru.sarmosov.dispatcher;

import ru.sarmosov.model.Command;
import ru.sarmosov.service.PeopleService;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;

import static ru.sarmosov.util.Commads.createPersonFromCommand;

public class Dispatcher implements Runnable {
    private final BlockingQueue<Command> queue;
    private final PeopleService peopleService;

    public Dispatcher(BlockingQueue<Command> queue, PeopleService peopleService) {
        this.queue = queue;
        this.peopleService = peopleService;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Command command = queue.take();
                processCommand(command);
                Thread.sleep(1000);
            } catch (InterruptedException | IOException | NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private void processCommand(Command command) throws IOException, NoSuchFieldException, IllegalAccessException {
        switch (command.getCommand()) {
            case "CREATE":
                peopleService.cretePerson(createPersonFromCommand(command));
                break;
            case "GET":
                System.out.println(peopleService.getPerson(command.getId()));
                break;
            case "UPDATE":
                peopleService.updatePersonField(command.getId(), command.getArg(), command.getFieldName());
                break;
            case "DELETE":
                peopleService.deletePerson(command.getId());
                break;
            default:
                System.out.println("Unknown command: " + command);
        }
    }

}
