package com.neoflex.test.learning.controller;

import com.neoflex.test.learning.entity.Student;
import com.neoflex.test.learning.service.LearnService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log
public class LearnController {
    private final LearnService learnService;

    @GetMapping("/get_all")
    public List<Student> getAllStudents() {
        log.info("call_method_get_all_students");
        return learnService.getAllStudent();
    }

    @GetMapping("/get_student")
    public Student getStudents(@RequestParam long id) {
        log.info("call_method_get_students_for_id");
        return learnService.getStudent(id);
    }

    @PostMapping("/get_student")
    public Student getStudents(@RequestBody Student student) {
        log.info("call_method_get_students_for_id");
        return learnService.getStudent(student.getId());
    }

    @GetMapping("/set_student")
    public Student setStudent() {
        log.info("call_method_set_student");
        return learnService.sendStudent();
    }

    @PostMapping("/inject_student")
    public Student injectStudent(@RequestBody Student student) {
        return learnService.injectStudent(student);
    }

    @GetMapping("/del_student/{id}")
    public String deleteStudent(@PathVariable long id) {
        log.info("call_method_delete_student_for_id");
        learnService.deleteStudent(id)
        ;
        return "Student" + " " + id + " " + "was deleted";
    }
}
