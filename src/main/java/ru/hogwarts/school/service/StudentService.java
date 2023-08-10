package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.DataNotFoundException;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class StudentService {
    private final Map<Long, Student> map = new HashMap<>(); // завести HashMap
    private Long COUNTER = 1L;   // создаем счетчик идентификатора, который будет инкрементироваться при каждом добавлении нового объекта модели в HashMap.

// добавляем CRUD операции

    public Student getById(Long id) {    // вернуть по Id
        return map.get(id);
    }

    public Collection<Student> getAll() {   //вернуть всех
        return map.values();
    }

    public Collection <Student> getByAge(int age){
        return map.values().stream()
                .filter(student -> student.getAge()==age)
                .toList();

    }   // фильтрация по возрасту

    public Student create(Student student) {
        Long nextId = COUNTER++;                // генерация ID
        student.setId(nextId);                        // и ставим этот Id студенту который пришел
        map.put(student.getId(), student);        // создаем студента
        return student;

    }

    public Student update(Long id, Student student) {
        if (!map.containsKey(id)) {
            throw new DataNotFoundException();
        }
        Student exsitingStudent = map.get(id); // существующий студент
        exsitingStudent.setName(student.getName()); // обновим его поля
        exsitingStudent.setAge(student.getAge());
        return exsitingStudent;
    }

    public void delete(Long id) {
        if (map.remove(id) == null) {
            throw new DataNotFoundException();
        }

    }

}
