package com.example.spring_jdbc_template.service;

import com.example.spring_jdbc_template.entity.User;
import com.example.spring_jdbc_template.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public List<User> getAllUser(){
         return userRepository.findAll();
    }

    public User getUserById(int id){
    return userRepository.findById(id);
    }

    public void createUser(User user){
         userRepository.save(user);
    }

    public void updateUser(User user){
        userRepository.update(user);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

}

