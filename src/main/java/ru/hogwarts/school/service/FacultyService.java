package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.DataNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class FacultyService {
    private final Map<Long, Faculty> map = new HashMap<>(); // завести HashMap
    private Long COUNTER = 1L;   // создаем счетчик идентификатора, который будет инкрементироваться при каждом добавлении нового объекта модели в HashMap.

// добавляем CRUD операции

    public Faculty getById(Long id) {    // вернуть по Id
        return map.get(id);
    }
    public Collection <Faculty> getByColor(String color){
        return map.values().stream()
                .filter(faculty -> faculty.getColor().equalsIgnoreCase(color))
                .toList();
    }

    public Collection<Faculty> getAll() {   //вернуть всех
        return map.values();
    }

    public Faculty create(Faculty faculty) {
        Long nextId = COUNTER++;                // генерация ID
        faculty.setId(nextId);                        // и ставим этот Id студенту который пришел
        map.put(faculty.getId(), faculty);        // создаем студента
        return faculty;

    }

    public Faculty update(Long id, Faculty faculty) {
        if (!map.containsKey(id)) {
            throw new DataNotFoundException();
        }
        Faculty exsitingFaculty = map.get(id); // существующий студент
        exsitingFaculty.setName(faculty.getName()); // обновим его поля
        exsitingFaculty.setColor(faculty.getColor());
        return exsitingFaculty;
    }

    public void delete(Long id) {
        if (map.remove(id) == null) {
            throw new DataNotFoundException();
        }

    }
}


