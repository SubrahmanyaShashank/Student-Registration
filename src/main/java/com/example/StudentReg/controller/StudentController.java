package com.example.StudentReg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.StudentReg.domain.Student;
import com.example.StudentReg.service.StudentService;

@RestController
public class StudentController {
	
	@Autowired
	private StudentService service;
	
	
	 @GetMapping("/")
     public String viewHomePage(Model model) {
         List<Student> liststudent = service.listAll();
         model.addAttribute("liststudent", liststudent);
         System.out.print("Get / ");    
         return "index";
     }
     @GetMapping("/new")
     public String add(Model model) {
         model.addAttribute("student", new Student());
         return "new";
     }
     
	@PostMapping("/save")
	public String savestudent(@RequestBody Student std) {
		service.save(std);
		return "redirect:/";
	}
	
	@RequestMapping("/edit/{id}")
    public ModelAndView showEditStudentPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("new");
        Student std = service.get(id);
        mav.addObject("student", std);
        return mav;
        
    }
   
	
	@DeleteMapping("/delete")
	public String daletestudent(@PathVariable int id) {
		service.delete(id);
		return "redirect/";
		
		
	}
	

}
