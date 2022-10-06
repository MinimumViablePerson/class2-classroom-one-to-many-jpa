package com.classroom.classroom;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Student {
  @Id
  @GeneratedValue
  public Integer id;
  public String name;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "teacherId", nullable = false)
  public Teacher teacher;

  public Student() {
  }
}

@RestController
class StudentController {
  @Autowired
  private StudentRepo studentRepo;

  @Autowired
  private TeacherRepo teacherRepo;

  @GetMapping("/students")
  public List<Student> getAllStudents() {
    return studentRepo.findAll();
  }

  @PostMapping("/teachers/{teacherId}/students")
  public Student createStudent(@RequestBody Student studentData, @PathVariable Integer teacherId) {
    studentData.teacher = teacherRepo.findById(teacherId).get();
    return studentRepo.save(studentData);
  }
}

interface StudentRepo extends JpaRepository<Student, Integer> {
}

interface TeacherRepo extends JpaRepository<Teacher, Integer> {
}