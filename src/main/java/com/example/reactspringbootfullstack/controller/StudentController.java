package com.example.reactspringbootfullstack.controller;

import com.example.reactspringbootfullstack.service.StudentService;
import com.example.reactspringbootfullstack.student.Student;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/students")
@AllArgsConstructor
public class StudentController {

    @Autowired
    private final StudentService studentService;

    @PostMapping
    public void save(@Valid @RequestBody Student student){
        studentService.addStudent(student);
    }

    @GetMapping
    public List<Student> getAllStudents(){
//        throw
        return studentService.getAllStudents();
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
    }
}
