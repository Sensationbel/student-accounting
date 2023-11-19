package by.bulaukin.studentaccounting;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Getter
@Component
public class StudentsRepository {

    private Map<Integer, Students> studentsList = new HashMap<>();
}
