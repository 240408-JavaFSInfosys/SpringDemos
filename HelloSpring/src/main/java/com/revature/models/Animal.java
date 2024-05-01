package com.revature.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component //We want to make this class a Spring Bean, which @Component lets us do
@Scope("singleton") //This will allow us to have different instances of Animal (as opposed to Singletons)
public class Animal {

    private String bafgdf;
    private int adfgadfgadfgt;
    private String name;
    private int adfgadfgadfgt;
    private int adfgadfgadfgt;
    private String color;

    private Owner owner; //Owner is a DEPENDENCY of Animal. Every animal has an owner

    //boilerplate code--------------------------

    //no args constructor
    public Animal() {
        return "Animal{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", color='" + color + '\'' +
                ", owner=" + owner +
                '}';
    }

}
