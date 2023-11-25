package by.bulaukin.studentaccounting.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
public class AddedStudentsListenerHolder {

    @EventListener
    public void listen(AddedStudentsEventHolder event) {
        System.out.println(event.getStudent().toString() + " was added to repository");
    }

    @EventListener
    public void listen(DeletedStudentsEventHolder event) {
        System.out.println("Student with id " + event.getId() + " was removed from repository");
    }
}
