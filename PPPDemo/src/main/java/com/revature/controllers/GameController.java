package com.revature.controllers;

import com.revature.daos.GameDAO;
import com.revature.daos.UserDAO;
import com.revature.models.Game;
import com.revature.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/games")
public class GameController {

    private GameDAO gameDAO;
    private UserDAO userDAO;

    @Autowired
    public GameController(GameDAO gameDAO, UserDAO userDAO) {
        this.gameDAO = gameDAO;
        this.userDAO = userDAO;
    }


    //this method will insert a new Game into the games table
    @PostMapping("/{userId}")
    public ResponseEntity<Game> insertGame(@RequestBody Game game, @PathVariable int userId){

        //we want to send a user id int from postman, but we only have the User object in the Game class.
        //we have to send in the user id as a Path Variable, and get the user by ID, to attach to the Game.
        User u = userDAO.findById(userId).get();

        //Why .get()?^ findById() returns an OPTIONAL. This basically means it may find the user, or it may not
        //In other words, it may be a User object, or it may be null.
        //.get() will return the User object if it exists.

        game.setUser(u); //attach the given User to the game object before we send it to the DB

        Game g = gameDAO.save(game); //Remember, save() also RETURNS the object that was saved

        return ResponseEntity.status(201).body(g);

    }

    //this method will take an entire game object and replace an existing game with it
    @PutMapping("/{userId}")
    public ResponseEntity<Game> updateGame(@RequestBody Game game, @PathVariable int userId){

        User u = userDAO.findById(userId).get();
        
        game.setUser(u);

        Game g = gameDAO.save(game); //updates and inserts use the SAME JPA METHOD, save()

        //how does the DB know if it's an update or insert? It looks for an existing PK
        //if we send a Game with no PK, it knows to create a new game
        //if we send a Game with an EXISTING PK, it knows to update the existing game

        return ResponseEntity.ok(g);

    }


}
