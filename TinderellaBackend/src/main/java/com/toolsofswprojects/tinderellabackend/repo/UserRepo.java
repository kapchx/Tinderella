package com.toolsofswprojects.tinderellabackend.repo;

import com.toolsofswprojects.tinderellabackend.model.User_t;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepo extends JpaRepository<User_t, Long> {
    void deleteUserById(Long id);

    Optional<User_t> findUserById(Long id);
}