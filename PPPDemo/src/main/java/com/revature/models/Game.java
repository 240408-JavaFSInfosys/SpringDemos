package com.revature.models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;


@Entity
@Table(name="games")
@Component
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gameId;
    private String title;
    private String genre;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "userId")
    private User user;

    /* What is going on here^???

    This is how we establish a PK/FK relationship. Every Game has a User it belongs to.

    @ManyToOne - This is saying that "Game" is the foreign key side of the relationship

    fetch - defines whether the dependency (User) is eagerly or lazily loaded
        -Eager: the dependency is loaded ASAP. Lazy: the dependency is loaded when it's needed

    cascade - defines what happens to the dependency (game) when the parent (user) is changed
        so with cascade = ALL, if one record is deleted, all records associated will also try to delete
        with cascade = MERGE, cascade only happens for updates.

    @JoinColumn - this is the column that links the two tables together.
        Use the name of the Id field in the Parent
     */

    //Boilerplate code-----------------------------------

    //no args constructor
    public Game() {
    }

    //all args constructor
    public Game(int gameId, String title, String genre, User user) {
        this.gameId = gameId;
        this.title = title;
        this.genre = genre;
        this.user = user;
    }

    //getters and setters
    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //toString
    @Override
    public String toString() {
        return "Game{" +
                "gameId=" + gameId +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", user=" + user +
                '}';
    }
}
