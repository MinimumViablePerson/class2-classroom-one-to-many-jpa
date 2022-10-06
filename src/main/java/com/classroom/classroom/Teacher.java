package com.classroom.classroom;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Entity
public class Teacher {
  @Id
  @GeneratedValue
  public Integer id;
  public String name;

  @OneToMany(mappedBy = "teacher")
  public Set<Student> students;

  public Teacher() {
  }
}

@RestController
class TeacherController {
  @Autowired
  private TeacherRepo teacherRepo;

  @GetMapping("/teachers")
  public List<Teacher> getAllTeachers() {
    return teacherRepo.findAll();
  }

  @PostMapping("/teachers")
  public Teacher createTeacher(@RequestBody Teacher teacherData) {
    return teacherRepo.save(teacherData);
  }
}

interface TeacherRepo extends JpaRepository<Teacher, Integer> {
}