package by.bulaukin.studentaccounting;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.text.MessageFormat;
import java.util.Map;

@ShellComponent
@RequiredArgsConstructor
public class StudentsService {

    private final StudentsRepository repository;

    private Students student;

    @EventListener(ApplicationPreparedEvent.class)
    public void listenAddStudents(ApplicationPreparedEvent event) {
        printStudents();
        a(event);
    }

    private void a(ApplicationPreparedEvent event) {
        System.out.println("Listener: " + event.getArgs().toString());
    }

    @ShellMethod(value = "Print all students: ", key = "print")
    public void printStudents() {
        repository.getStudentsList().values().forEach(s -> System.out.println(s.toString()));
    }

    @ShellMethod(value = "Add students to repository.", key = "add")
    public String addStudent(String firstName, String lastName,int age){
        Map<Integer, Students> list = repository.getStudentsList();
        int id = list.keySet().stream().reduce((e1,e2) -> e2).orElse(0) + 1;

        this.student = new Students(id, firstName, lastName, age);
        list.put(id, student);

        return MessageFormat.format("Student with id {0} added to repository.", id);
    }

    @ShellMethod(value = "Delete student by id", key = "delete")
    public void delete(int id) {
        if (checkId(id)) {
            repository.getStudentsList().remove(id);
        }
    }

    @ShellMethod(value = "Delete all students", key = "deleteAll")
    public void deleteAll(){
        repository.getStudentsList().clear();
    }

    private boolean checkId(int id) {
        return repository.getStudentsList().values().stream().map(Students::getId).anyMatch(e -> e == id);
    }
}
