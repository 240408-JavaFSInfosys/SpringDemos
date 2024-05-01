package com.revature.models;

        import org.springframework.context.annotation.Scope;
        import org.springframework.stereotype.Component;

@Component //This is a stereotype annotation - it makes a Class a Spring Bean
public class Owner {

    private String name;
    private int age;

    //boilerplate code---------------------------------------

    //no args constructor
    public Owner() {
    }

    //all args constructor
    public Owner(String name, int age) {
        this.name = name;
        this.age = age;
    }

    //getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    //toString
    @Override
    public String toString() {
        return "Owner{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

}
