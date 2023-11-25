package by.bulaukin.studentaccounting.add_default;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class DefaultStudentsEventHolder extends ApplicationEvent {

    private DefaultStudentsWriter studentsWriter;

    public DefaultStudentsEventHolder(Object source, DefaultStudentsWriter defaultStudentsWriter) {
        super(source);
        this.studentsWriter = defaultStudentsWriter;
    }
}
