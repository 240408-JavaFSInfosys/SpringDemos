package com.revature.daos;

import com.revature.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
    By extending JpaRepository, we get access to various DAO methods that we don't need to write

    JpaRepository takes two generics:
    -The Java Model AND Database table that we're working with
    -The data type of the primary key of that table
 */

@Repository //one of the 4 stereotype annotations - makes a class a bean
public interface UserDAO extends JpaRepository<User, Integer> {

    //I want to be able to find a User by their username
    //Since JpaRepository doesn't have that method, we have to make one ourselves
    //Spring Data IS smart enough to implement this method for us. We just need to define it.
    public User findByUsername(String username);

    //NOTE: The method MUST BE NAMED "findByXYZ", otherwise it won't work as intended.

    //How does Spring Data know? It's based on the name of the FIELD IN THE CLASS

    //ex: findByPassword
    //ex: findByUsernameAndPassword if we wanted to find by multiple fields
    //There are A LOT of options for custom DAO methods, feel free to look into them, or ask chatGPT etc

}
