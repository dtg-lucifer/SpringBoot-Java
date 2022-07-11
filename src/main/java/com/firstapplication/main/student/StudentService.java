package com.firstapplication.main.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService (StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent (Student student) {
        Optional<Student> studentOptional = studentRepository.getStudentsByEmail(student.getEmail());
        if (studentOptional.isPresent()) throw new IllegalStateException("EMAIL TAKEN");
        studentRepository.save(student);
    }

    public void deleteStudent (Long studentId) {
        boolean isExists = studentRepository.existsById(studentId);
        if (!isExists) throw new IllegalStateException("Student with id " + studentId + " doest not exists.");
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent (Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("Student with id " + studentId + " doest not exists."));
        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }
        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional = studentRepository.getStudentsByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("Email already taken.");
            }
            student.setEmail(email);
        }
    }
}
