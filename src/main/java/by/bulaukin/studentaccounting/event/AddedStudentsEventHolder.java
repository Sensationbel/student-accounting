package by.bulaukin.studentaccounting.event;

import by.bulaukin.studentaccounting.Students;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class AddedStudentsEventHolder extends ApplicationEvent {

    private Students student;

    public AddedStudentsEventHolder(Object source, Students student) {
        super(source);
        this.student = student;
    }
}
