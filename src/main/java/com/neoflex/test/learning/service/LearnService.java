package com.neoflex.test.learning.service;

import com.neoflex.test.learning.dao.RepositoryMethods;
import com.neoflex.test.learning.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LearnService {
    private final RepositoryMethods repositoryMethods;

    public List<Student> getAllStudent() {
        return repositoryMethods.findAll();
    }

    public Student sendStudent() {
        Student student = new Student();
        return repositoryMethods.save(student);
    }

    public Student injectStudent(Student student) {
        return repositoryMethods.save(student);
    }

    public void deleteStudent(long id) {
        repositoryMethods.deleteById(id);
    }

    public Student getStudent(long id) {
        Student student = null;
        Optional<Student> optional = repositoryMethods.findById(id);
        if (optional.isPresent()) {
            student = optional.get();
        }
        return student;
    }

}
