package com.revature.controllers;

import com.revature.daos.GameDAO;
import com.revature.daos.UserDAO;
import com.revature.models.Game;
import com.revature.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    //this method will update ONLY the title of a game
    @PatchMapping("/{gameId}")
    public ResponseEntity<Object> updateGameTitle(@RequestBody String title, @PathVariable int gameId){

        //get the game by Id, set the new title (setter), save it back to the DB :)

        //this time, we'll use Optionals as they were meant to be used - to prevent NullPointerExceptions
        Optional<Game> g = gameDAO.findById(gameId);

        //if the Optional is empty, send an error message, otherwise send the updated game
        if(g.isEmpty()){
            //This will return a String message telling the user what went wrong
            //Note the method's return type needs to be ResponseEntity<Optional> for this to work
            return ResponseEntity.status(404).body("No game found with ID of: " + gameId);
        }

        //extract the Game from the Optional
        Game game = g.get();

        //update the title, using the setter
        game.setTitle(title);

        //finally, save the updated game back to the DB
        gameDAO.save(game);

        return ResponseEntity.accepted().body(game);

    }

    //this method will delete a game by its ID
    @DeleteMapping("/{gameId}")
    public ResponseEntity<Object> deleteGame(@PathVariable int gameId){

        //first we'll get the game by Id to see if it exists
        //error message if not!!
        Optional<Game> g = gameDAO.findById(gameId);

        if(g.isEmpty()){
            return ResponseEntity.status(404).body("No game found with ID of: " + gameId);
        }

        Game game = g.get();

        //now we can do the delete!
        gameDAO.deleteById(gameId);

        //we could send back the entire game, but let's send a confirmation message instead
        return ResponseEntity.accepted().body("Game " + game.getTitle() + " has been deleted");

    }

}
