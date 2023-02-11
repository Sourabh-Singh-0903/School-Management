package com.school.management.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.school.management.model.UserDtls;
import com.school.management.service.UserService;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/signin")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/createUser")
    public String createUser(@ModelAttribute UserDtls user, @RequestParam("image") MultipartFile file,
            HttpSession session) {
        try {
            boolean f = userService.checkEmail(user.getEmail());
            if (f) {
                session.setAttribute("msg", "Email id alredy exists");
            } else {
                user.setImageUrl(file.getOriginalFilename());
                File saveFile = new ClassPathResource("static/img").getFile();
                Path path = Paths.get(saveFile.getAbsolutePath()+File.separator+file.getOriginalFilename());
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
               
                UserDtls userDtls = userService.createUser(user);
                if (userDtls != null) {
                    session.setAttribute("msg", "Register Successful");
                } else {
                    session.setAttribute("msg", "something wrong on server");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/register";
    }
}
