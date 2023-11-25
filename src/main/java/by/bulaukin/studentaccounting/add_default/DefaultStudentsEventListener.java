package by.bulaukin.studentaccounting.add_default;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DefaultStudentsEventListener {

    @EventListener
    public void listen(@NotNull DefaultStudentsEventHolder eventHolder) throws IOException {
        eventHolder.getStudentsWriter().addDefaultStudents();
    }
}
