package by.bulaukin.studentaccounting;

import by.bulaukin.studentaccounting.event.AddedStudentsEventHolder;
import by.bulaukin.studentaccounting.event.DeletedStudentsEventHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.text.MessageFormat;
import java.util.Map;

@ShellComponent
@RequiredArgsConstructor
public class StudentsService {

    private final StudentsRepository repository;
    private final ApplicationEventPublisher publisher;


    @ShellMethod(value = "Print all students: ", key = "print")
    public void printStudents() {
        repository.getStudentsList().values().forEach(s -> System.out.println(s.toString()));
    }

    @ShellMethod(value = "Add students to repository.", key = "add")
    public void addStudent(String firstName, String lastName,int age){
        Map<Integer, Students> list = repository.getStudentsList();
        int id = list.keySet().stream().reduce((e1,e2) -> e2).orElse(0) + 1;

        Students student = new Students(id, firstName, lastName, age);
        list.put(id, student);
        publisher.publishEvent(new AddedStudentsEventHolder(this, student));
    }

    @ShellMethod(value = "Delete student by id.", key = "delete")
    public void delete(int id) {
        if (checkId(id)) {
            repository.getStudentsList().remove(id);
            publisher.publishEvent(new DeletedStudentsEventHolder(this, id));
        }
    }

    @ShellMethod(value = "Delete all students.", key = "delete-all")
    public void deleteAll(){
        repository.getStudentsList().clear();
    }

    private boolean checkId(int id) {
        return repository.getStudentsList().values().stream().map(Students::getId).anyMatch(e -> e == id);
    }
}
