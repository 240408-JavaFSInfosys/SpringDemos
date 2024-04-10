package com.revature.models;

import org.springframework.stereotype.Component;

@Component //We want to make this class a Spring Bean, which @Component lets us do
public class Animal {

    private String type;
    private String name;
    private int weight;
    private String color;

    private Owner owner; //Owner is a DEPENDENCY of Animal. Every animal has an owner

    //boilerplate code--------------------------

    //no args constructor
    public Animal() {
    }

    //all args constructor
    public Animal(String type, String name, int weight, String color, Owner owner) {
        this.type = type;
        this.name = name;
        this.weight = weight;
        this.color = color;
        this.owner = owner;
    }

    //getters and setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    //toString
    @Override
    public String toString() {
        return "Animal{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", color='" + color + '\'' +
                ", owner=" + owner +
                '}';
    }

}
