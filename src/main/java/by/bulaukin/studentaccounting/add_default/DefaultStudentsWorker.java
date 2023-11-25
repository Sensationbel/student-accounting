package by.bulaukin.studentaccounting.add_default;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

import java.io.IOException;

@RequiredArgsConstructor
public class DefaultStudentsWorker {

    private final DefaultStudentsWriter defaultStudentsWriter;
    private final ApplicationEventPublisher publisher;

//    @PostConstruct
    public void startWrite() {
        publisher.publishEvent(new DefaultStudentsEventHolder(this, defaultStudentsWriter));
    }

}
