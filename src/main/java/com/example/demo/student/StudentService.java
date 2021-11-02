package com.example.demo.student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
  private final StudentRepository studentRepository;

  @Autowired
  public StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  public List<Student> getStudents() {
    return studentRepository.findAll();
  }

  public void createNewStudent(Student student) {
    Optional<Student> foundStudent = studentRepository.findStudentByEmail(
      student.getEmail()
    );

    if (foundStudent.isPresent()) {
      throw new IllegalStateException(
        "The provided email has already been taken"
      );
    }

    studentRepository.save(student);
  }

  public void deleteStudent(Long studentId) {
    boolean studentExists = studentRepository.existsById((studentId));

    if (!studentExists) {
      throw new IllegalStateException(
        "Student with id " + studentId + " does not exist"
      );
    }

    studentRepository.deleteById((studentId));
  }

  @Transactional
  public void updateStudent(Long studentId, String name, String email) {
    Student student = studentRepository
      .findById(studentId)
      .orElseThrow(
        () ->
          new IllegalStateException(
            "Student with id " + studentId + " does not exist"
          )
      );

    if (
      name != null &&
      name.length() > 0 &&
      !Objects.equals(student.getName(), name)
    ) {
      student.setName(name);
    }

    if (
      email != null &&
      email.length() > 0 &&
      !Objects.equals(student.getEmail(), email)
    ) {
      Optional<Student> optionalStudent = studentRepository.findStudentByEmail(
        email
      );

      if (optionalStudent.isPresent()) {
        throw new IllegalStateException(
          "The provided email has already been taken"
        );
      }

      student.setEmail(email);
    }
  }
}
