package com.revature.controllers;

import com.revature.daos.UserDAO;
import com.revature.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //This makes a class a bean, and converts every response to JSON for us
@RequestMapping("/users") //All HTTP Requests ending in /users will be handled by this controller
public class UserController {

    private UserDAO userDAO;

    //Constructor injection (because this controller depends on the UserDAO)
    @Autowired
    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    //A method that inserts a new user (POST request)
    @PostMapping
    public ResponseEntity<User> insertUser(@RequestBody User user){

        //if the insert succeeds, this will hold the inserted user
        //if the insert fails, this will hold null
        User u = userDAO.save(user);


        if(u == null){
            //this will a response with status 500, and NO RESPONSE BODY (which is what build() does)
            return ResponseEntity.internalServerError().build();
        }

        //this will return a response with status 201 (created), and also send the user back for us to see
        return ResponseEntity.status(201).body(u);

    }

    //This method will get all users (GET request)
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){

        //not much error handling needed, since there's no user input
        List<User> users = userDAO.findAll();

        return ResponseEntity.ok(users);

    }

    //This method will get a user by their username (GET request with a PathVariable)
    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username){

        //is the user is not found, User u will be null
        //otherwise, it will contain the User with the given username
        User u = userDAO.findByUsername(username);

        if(u == null){
            //return a ResponseEntity with status 404 (not found)
            return ResponseEntity.notFound().build();
        }

        //return a ResponseEntity with status 200 (ok), and the user in the body
        return ResponseEntity.ok(u);

    }


    //This method will get a user by their ID

}
