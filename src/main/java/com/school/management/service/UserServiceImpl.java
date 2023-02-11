package com.school.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.school.management.model.UserDtls;
import com.school.management.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;



    @Override
    public UserDtls createUser(UserDtls user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        return userRepository.save(user);
    }

    @Override
    public boolean checkEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    

   

    @Override
    public List<UserDtls> findAllStudents(String nameString) {
        return userRepository.findAllByStudentTeacher(nameString);

    }

    @Override
    public List<UserDtls> findAllTeachers(String nameString) {
        return userRepository.findAllByStudentTeacher(nameString);
    }
    
}
