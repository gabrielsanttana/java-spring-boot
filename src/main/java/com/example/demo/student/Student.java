package com.example.demo.student;

import java.time.LocalDate;
import java.time.Period;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
public class Student {
  @Id
  @SequenceGenerator(
    name = "student_sequence",
    sequenceName = "student_sequence",
    allocationSize = 1
  )
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "student_sequence"
  )
  private Long id;

  private String name;
  private String email;
  private LocalDate dateOfBirth;

  @Transient
  private Integer age;

  public Student() {}

  public Student(Long id, String name, String email, LocalDate dateOfBirth) {
    this.id = id;
    this.name = name;
    this.email = email;
  }

  public Student(String name, String email, LocalDate dateOfBirth) {
    this.name = name;
    this.email = email;
    this.dateOfBirth = dateOfBirth;
  }

  public Long getId() {
    return id;
  }

  public Integer getAge() {
    return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return (
      "Student [age=" +
      age +
      ", dateOfBirth=" +
      dateOfBirth +
      ", email=" +
      email +
      ", id=" +
      id +
      ", name=" +
      name +
      "]"
    );
  }
}
