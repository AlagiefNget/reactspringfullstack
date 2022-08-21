package com.example.reactspringbootfullstack.service;

import com.example.reactspringbootfullstack.repository.StudentRepository;
import com.example.reactspringbootfullstack.student.BadRequestException;
import com.example.reactspringbootfullstack.student.Student;
import com.example.reactspringbootfullstack.student.StudentNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public void addStudent(Student student) {
        Boolean existEmail = studentRepository.selectExistsEmail(student.getEmail());
        if(existEmail){
            throw new BadRequestException("Email "+student.getEmail() + " already exist");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        // check if student exists
        if(!studentRepository.existsById(studentId)) {
            throw new StudentNotFoundException(
                    "Student with id " + studentId + " does not exists");
        }
        studentRepository.deleteById(studentId);
    }
}
