package com.toolsofswprojects.tinderellabackend.repo;

import com.toolsofswprojects.tinderellabackend.model.User_t;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User_t, Long> {
    void deleteEmployeeById(Long id);

    Optional<User_t> findEmployeeById(Long id);
}