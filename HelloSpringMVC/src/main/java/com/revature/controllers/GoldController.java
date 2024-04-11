package com.revature.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/{amount}") //Here, we're adding a PATH VARIABLE, which will let us specify the amount of gold to get.
    //Any GET request ending in /gold/someNumber will go to this method
    public ResponseEntity<String> getSomeGold(@PathVariable int amount){ //@PathVariable lets us retrieve the path variable

        //Let's do some error handling to make sure the amount is valid. Status 400 is generic "bad request"
        //Side note: we could define some "InvalidGoldInputException to make more specific custom error handling"

        if(amount < 0){
            return ResponseEntity.status(400).body("Cannot retrieve negative gold!");
        }

        if(amount > 1000){
            return ResponseEntity.status(400).body("You do not have that much gold!");
        }

        //If the user gave a valid number, return that amount of gold
        return ResponseEntity.ok("Retrieved " + amount + " units of gold");

        //using shorthand to return here - ok() sets a status of 200 and we can put the response body inside the method call

    }


    //This method will be used to deposit gold into the bank
    @PostMapping //any POST request ending in /gold will go here
    public ResponseEntity<String> addGold(@RequestBody int amount){

        //@RequestBody takes the BODY of the HTTP REQUEST and puts it in a variable

        //Let's add error handling for amounts over 10000
        if(amount > 10000){
            return ResponseEntity.badRequest().body("Cannot deposit over 10000 units of gold per day!");
        }


        //let's return a confirmation message telling the user how much gold they added
        //In a real app, the amount would eventually hit the database and be saved

        return ResponseEntity.accepted().body("Deposited " + amount + " units of gold");
        //202 is the "accepted" status code

    }

    //This method takes a gold ITEM and requires us to specify how many karats it is
    @PostMapping("/item/{karats}")
    public ResponseEntity<String> addGoldItem(@RequestBody String item, @PathVariable int karats){

        //Realistically, we could (and should) create a GoldItem class to hold the item and karats
        //But in this case, I just want to show a method that includes everything we've learned

        //Assume the item hit the database, here's the success message
        return ResponseEntity.status(201).body("Added " + item + " with " + karats + " karats of gold.");


    }



}
