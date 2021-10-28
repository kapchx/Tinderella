package com.toolsofswprojects.tinderellabackend.service;

import com.toolsofswprojects.tinderellabackend.exception.UserNotFoundException;
import com.toolsofswprojects.tinderellabackend.model.User_t;
import com.toolsofswprojects.tinderellabackend.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User_t addUser(User_t user){
        user.setUserCode(UUID.randomUUID().toString());
        return userRepo.save(user);

    }

    public List<User_t> findAllUsers(){
        return userRepo.findAll();
    }

    public User_t updateUser(User_t user){
        return userRepo.save(user);
    }

    public User_t findUserById(Long id){
        return userRepo.findUserById(id).
                orElseThrow(() -> new UserNotFoundException(" User by id " + id + " was not found"));
    }


    public void deleteUser(Long id){
        userRepo.deleteUserById(id);
    }
    }
