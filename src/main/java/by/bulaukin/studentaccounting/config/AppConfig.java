package by.bulaukin.studentaccounting.config;


import by.bulaukin.studentaccounting.add_default.DefaultStudentsWriter;
import by.bulaukin.studentaccounting.add_default.DefaultStudentsWorker;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnBean(DefaultStudentsWriter.class)
public class AppConfig {

    @Bean
    public DefaultStudentsWorker defaultStudentsWorker(DefaultStudentsWriter studentsWriter, ApplicationEventPublisher publisher) {
        System.out.println("default");
        return new DefaultStudentsWorker(studentsWriter, publisher);
    }
}
