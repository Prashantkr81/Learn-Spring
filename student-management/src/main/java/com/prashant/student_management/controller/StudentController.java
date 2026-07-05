package com.prashant.student_management.controller;


import com.prashant.student_management.entity.Student;
import com.prashant.student_management.repository.StudentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    public final StudentRepository studentRepo ;

    public StudentController(StudentRepository studentRepo){
        this.studentRepo = studentRepo;
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {

        return studentRepo.save(student);

    }
    @GetMapping
    public List<Student> getAllStudents() {

        return studentRepo.findAll();

    }
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {

        return studentRepo.findById(id).orElse(null);

    }
    @DeleteMapping("/{id}")
    public String deleteStudentById(@PathVariable Long id){

        studentRepo.deleteById(id);

        return "Student Deleted Successfull" ;
    }

    @PutMapping("/{id}")
    public Student updateStudentById(@PathVariable Long id, @RequestBody Student updatedStudent){

        Student student= studentRepo.findById(id).orElse(null);

        if(student == null) return null;

        student.setName(updatedStudent.getName());
        student.setEmail(updatedStudent.getEmail());
        student.setAge(updatedStudent.getAge());
        student.setCourse(updatedStudent.getCourse());

        return studentRepo.save(student);
    }


}
