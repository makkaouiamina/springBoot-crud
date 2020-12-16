package com.springbootcrud.crudapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootcrud.crudapp.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
