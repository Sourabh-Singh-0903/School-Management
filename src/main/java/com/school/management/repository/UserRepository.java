package com.school.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.school.management.model.UserDtls;

public interface UserRepository extends JpaRepository<UserDtls,Integer> {
    
    public boolean existsByEmail(String email);

    public UserDtls findByEmail(String email);

    public List<UserDtls> findAllByStudentTeacher(String nameString);
}
