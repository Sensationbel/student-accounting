package by.bulaukin.studentaccounting.add_default;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;

import java.io.IOException;

@RequiredArgsConstructor
public class DefaultStudentsWorker {

    private final DefaultStudentsWriter defaultStudentsWriter;
    private final ApplicationEventPublisher publisher;

    @EventListener(ApplicationStartedEvent.class)
    public void startWrite() {
        publisher.publishEvent(new DefaultStudentsEventHolder(this, defaultStudentsWriter));
    }

}
