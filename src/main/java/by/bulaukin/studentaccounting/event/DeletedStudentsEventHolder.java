package by.bulaukin.studentaccounting.event;

import by.bulaukin.studentaccounting.Students;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

@Getter
public class DeletedStudentsEventHolder extends ApplicationEvent {

    private int id;
    public DeletedStudentsEventHolder(Object source, int id) {
        super(source);
        this.id = id;
    }
}
