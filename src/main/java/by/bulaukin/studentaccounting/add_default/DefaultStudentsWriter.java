package by.bulaukin.studentaccounting.add_default;

import by.bulaukin.studentaccounting.Students;
import by.bulaukin.studentaccounting.StudentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.IOException;
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

    public void addDefaultStudents() throws IOException {
        Map<Integer, Students> students = repository.getStudentsList();

        List<String> initData = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);

        initData.forEach(data -> {
            Students student = createStudents(data);
            students.put(student.getId(), student);
        });
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
