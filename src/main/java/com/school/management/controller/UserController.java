package com.school.management.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.school.management.model.UserDtls;
import com.school.management.repository.UserRepository;
import com.school.management.service.UserService;

@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;
    
    @ModelAttribute
    private void userDetails(Model m, Principal p){
        String email = p.getName();
        UserDtls user = userRepository.findByEmail(email);
        m.addAttribute("user", user);
    }

    @GetMapping("/")
    public String home() {
        return "user/home";
    }

    @GetMapping("/profile")
    public String profile() {
        return "user/home";
    }

    @GetMapping("students")
    public String getStudents(Model theModel) {
        String nameString = "student";
        List<UserDtls> students = userService.findAllStudents(nameString);
        theModel.addAttribute("students",students);
        return "user/students";
    }

    @GetMapping("teachers")
    public String getTeachers(Model theModel) {
        String nameString = "teacher";
        List<UserDtls> teachers = userRepository.findAllByStudentTeacher(nameString);
        theModel.addAttribute("teachers",teachers);
        return "user/teachers";
    }
}
