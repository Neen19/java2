package dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.factory.Mappers;
import ru.sarmosov.dao.PeopleDAO;
import ru.sarmosov.dao.PersonInfoDAO;
import ru.sarmosov.enums.Subject;
import ru.sarmosov.model.*;
import ru.sarmosov.service.PeopleService;
import ru.sarmosov.util.JsonUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class Test {

    @org.junit.jupiter.api.Test
    void test() throws IOException {
        PeopleDAO dao = new PeopleDAO();
        Person person = dao.findById("27");
        System.out.println(person);
        dao.close();
    }

    @org.junit.jupiter.api.Test
    void tetet() throws IOException {
        Command command = new Command();
        command.setCommand("CREATE");
//        command.setId("28");
        command.setStudent(true);
        command.setStudentObj(new Student("name", 2004, "123321", Map.of(Subject.MATH, 12.)));
//        command.setFieldName("averageGrades");
        command.setArg(Map.of(Subject.BIOLOGY, 0.));
        File file = new File("commandsfolder/com.json");
        JsonUtil.saveToFile(command, file);
    }

//    String fullName, int birthYear, String phoneNumber, Map<Subject, Double> averageGrades

    @org.junit.jupiter.api.Test
    void tesssst() throws IOException, NoSuchFieldException, IllegalAccessException {
        Person person = new Teacher("name", 2121, "sdd", Subject.ENGLISH, 12);
        PeopleService service = new PeopleService(new PeopleDAO());
        service.updatePersonField("25", Subject.MATH, "subject");
    }



}
