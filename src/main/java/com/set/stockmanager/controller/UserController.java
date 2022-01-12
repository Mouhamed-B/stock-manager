package com.set.stockmanager.controller;

import com.set.stockmanager.model.User;
import com.set.stockmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Create - Insert a user
     * @param user - the user instance
     * @return - the saved user
     */
    @PostMapping("/")
    public User saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    /**
     * Read - retrieve all users
     * @return - a list of users
     */
    @CrossOrigin(origins = "*")
    @GetMapping("/")
    public Iterable<User> getUsers(){
        return userService.getUsers();
    }

    /**
     * Read - retrieve a single user
     * @param id - user id
     * @return - a user instance
     */
    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") int id){
        Optional<User> user = userService.getUser(id);
        if (user.isPresent()){
            return user.get();
        } else{
            return null;
        }

    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable("id") int id, @RequestBody User user){
        Optional<User> u = userService.getUser(id);
        if (u.isPresent()){
            User currentUser = u.get();

            String firstName = user.getFirstName();
            if (firstName != null && !firstName.equals(currentUser.getFirstName())){
                currentUser.setFirstName(firstName);
            }

            String lastName = user.getLastName();
            if (lastName!=null && !lastName.equals(currentUser.getLastName())){
                currentUser.setLastName(user.getLastName());
            }

            String login = user.getLogin();
            if (login!=null && !login.equals(currentUser.getLogin())){
                currentUser.setFirstName(user.getFirstName());
            }

            return userService.saveUser(currentUser);
        }
        else {
            return null;
        }
    }

    /**
     * Update - Update user password
     * @param id - user id
     * @param password - new password
     * @return - boolean : whether modification succeeded or not
     */
    @PostMapping("/{id}/password")
    public boolean updatePassword(@PathVariable("id") int id, @RequestBody String password){
        Optional<User> u = userService.getUser(id);
        if (u.isPresent() && password != null) {
            User currentUser = u.get();
            currentUser.setPassword(password);
            return userService.saveUser(currentUser).getPassword().equals(password);
        }
        return false;
    }

    /**
     * Update - Update user role
     * @param id - user id
     * @param status - new status
     * @return - boolean : whether modification succeeded or not
     */
    @PostMapping("/{id}/status")
    public boolean updateStatus(@PathVariable("id") int id, @RequestBody boolean status){
        Optional<User> u = userService.getUser(id);
        if (u.isPresent()) {
            User currentUser = u.get();
            currentUser.setAdmin(status);
            return userService.saveUser(currentUser).isAdmin()==status;
        }
        return false;
    }

    /**
     * Delete - delete a user
     * @param id - the user id
     */
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") int id){
        userService.deleteUser(id);
    }
}
