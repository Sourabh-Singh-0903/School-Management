package com.school.management.service;

import java.util.List;

import com.school.management.model.UserDtls;

public interface UserService {
    
    public UserDtls createUser(UserDtls user);
    public boolean checkEmail(String email);
    public List<UserDtls> findAllStudents(String nameString);
    public List<UserDtls> findAllTeachers(String nameString);
    
}
