package com.set.stockmanager.service;

import com.set.stockmanager.model.User;
import com.set.stockmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService{

    @Autowired
    private UserRepository userRepository;

    /**
     * Create - Insert a user
     * @param user - the user instance
     * @return - the saved user
     */
    public User saveUser(User user){
        return userRepository.save(user);
    }

    /**
     * Read - retrieve all users
     * @return - a list of users
     */
    public Iterable<User> getUsers(){
        return userRepository.findAll();
    }

    /**
     * Read - retrieve a single user
     * @param id - user id
     * @return - a user instance
     */
    public Optional<User> getUser(final int id){
        return userRepository.findById(id);
    }

    /**
     * Delete - delete a user
     * @param id - the user id
     */
    public void deleteUser(final int id){
        userRepository.deleteById(id);
    }
}
