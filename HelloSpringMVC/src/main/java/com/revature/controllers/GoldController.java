package com.revature.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController combines @Controller and @ResponseBody.
@RestController //This makes the class a Bean, and sets every response to come back as JSON automatically.
@RequestMapping(value="/gold") //Now every request coming to this API (at localhost:8080) that ends in /gold will route to this Controller
public class GoldController {

    //This Demo will use SpringMVC to send HTTP requests that either get or retrieve gold from some bank.


    //This method will be used to select all gold from the bank
    @GetMapping //This annotation will make any GET request to /gold route to this method.
    public ResponseEntity<String> getAllGold(){

        //We return a ResponseEntity, and set the status code to 200, and the response data to the string you see below.
        return ResponseEntity.status(200).body("Retrieved 1000 units of gold");
    }
    

    //This method will be used to select a certain amount of gold from the bank


    //This method will be used to deposit gold into the bank



}
