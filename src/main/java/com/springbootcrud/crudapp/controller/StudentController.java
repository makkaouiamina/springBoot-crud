package com.springbootcrud.crudapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springbootcrud.crudapp.entity.Student;
import com.springbootcrud.crudapp.service.StudentServiceImpl;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	
	private StudentServiceImpl studentService;
	
	@Autowired
	public StudentController(StudentServiceImpl theStudentService) {
		this.studentService = theStudentService;
	}
	
	@GetMapping("/list")
	public String listStudents(Model model) {
		List<Student> listStudents = studentService.findAll();
		model.addAttribute("listStudents", listStudents);
		
		return "listStudents";
	}
	
	@GetMapping("/add")
	public String addStudent(Model theModel) {
		
		// create model attribute to bind form data
		Student student = new Student();
		theModel.addAttribute("student", student);
		return "addStudent";
	}
	
	@PostMapping("/save")
	public String saveStudent(@ModelAttribute("student") Student student, RedirectAttributes redirectAttributes) {
		
		studentService.save(student);
		
		String successString = student.getFirstName()+" " +student.getLastName()+ " successfully added";
		
		redirectAttributes.addFlashAttribute("success", successString);		
		
		System.out.println(student.getFirstName());
		
		return "redirect:/student/list";
		
	}
	
	@GetMapping("/editform/{id}")
	public String showEditStudent(@PathVariable("id") long id, Model model) {
		
		Student student = studentService.get(id);
		
		model.addAttribute("student", student);
		
		return "editStudent";
	}
	
	
	@PostMapping("/edit/{id}")
	public String editStudent(@PathVariable("id") long id, @Valid Student student, RedirectAttributes redirectAttributes) {
		
		studentService.save(student);
		
        String successString = student.getFirstName()+" " +student.getLastName()+ " successfully updated!";
		
		redirectAttributes.addFlashAttribute("updateSuccess", successString);	
		
		return "redirect:/student/list";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteStudent(@PathVariable(name = "id") long id, RedirectAttributes redirectAttributes) {
		Student student = studentService.get(id);
		
		studentService.delete(id);
		
        String successString = student.getFirstName()+ " " +student.getLastName()+ " successfully deleted!";
		
		redirectAttributes.addFlashAttribute("deleteSuccess", successString);	
		
		return "redirect:/student/list";
	}
	
	@GetMapping("detail/{id}")
	public String getStudentDetails(@PathVariable("id") long id, Model model) {
		
		Student student = studentService.get(id);
		
		model.addAttribute("student", student);
		
		
		return "detailStudent";
	}

}
