package com.springbootcrud.crudapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springbootcrud.crudapp.entity.Student;
import com.springbootcrud.crudapp.repository.StudentRepository;

@Service
@Transactional
public class StudentServiceImpl {
	
    private StudentRepository studentRepo;
    
    @Autowired
    public StudentServiceImpl(StudentRepository theStudentRepo) {
    	this.studentRepo = theStudentRepo;
    }
    
    public List<Student> findAll(){
    	return studentRepo.findAll();
    }
    
    public void save(Student student) {
    	studentRepo.save(student);
    }
    
    public Student get(long id) {
    	return studentRepo.findById(id).get();
    }
    
    public void delete(long id) {
    	studentRepo.deleteById(id);
    }

}
