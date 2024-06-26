package com.revature.daos;

import com.revature.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameDAO extends JpaRepository<Game, Integer> {

    //find all games by user id
    public Game findByUserUserId(int userId);

}
