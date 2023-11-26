package by.bulaukin.studentaccounting.add_default;

import by.bulaukin.studentaccounting.Students;
import by.bulaukin.studentaccounting.StudentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@Component
@ConditionalOnProperty("app.students-add.enabled")
@RequiredArgsConstructor
public class DefaultStudentsWriter {

    private final StudentsRepository repository;
    @Value("${app.path-read}")
    private String path;

    public void addDefaultStudents() {
        Map<Integer, Students> students = repository.getStudentsList();
        try (InputStream stream = getClass().getResourceAsStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));){
            String line = null;
            while ((line = reader.readLine()) != null){
                Students student = createStudents(line);
                students.put(student.getId(), student);
            }
            reader.close();
        } catch ( IOException ex) {
            ex.printStackTrace();
        }

//        List<String> initData = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
//
//        initData.forEach(data -> {
//            Students student = createStudents(data);
//            students.put(student.getId(), student);
//        });
    }

    private Students createStudents(String data) {

        String[] split = data.split("\s+");
        int id = Integer.parseInt(split[0]);
        String firstName = split[1];
        String lastName = split[2];
        int age = Integer.parseInt(split[3]);
        return new Students(id, firstName, lastName, age);
    }
}
