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

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private User user;

    /* What is going on here???^

    This is how we establish a PK/FK relationship. Every Game has a User it belongs to.

    @ManyToOne - This is saying that "Game" if the foreign side of the relationship

    fetch - defines whether the dependency (User) is eagerly or lazily loaded
        -Eager: the dependency is loaded ASAP. Lazy: the dependency is loaded when it's needed

    cascade - defines what happens to the dependency (game) when the parent (user) is updated
        so with cascase = ALL, if a User is deleted, all the games associated with that user will also delete

    @JoinColumn - this is the column that links the two tables together.
        Use the name of the Id field in the Parent
     */

}   //TODO: boilerplate code-----------------------------------
