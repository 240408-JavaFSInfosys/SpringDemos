package com.revature.HelloSpring;

import com.revature.models.Animal;
import com.revature.models.Owner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class HelloSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);

		//Create an ApplicationContext object based off of our applicationContext.xml file
		//This object will serve as our Spring IoC Container, and manage our Beans + Dep. Injection
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");

		//Now that we have a Spring Container (AppContext), we can ask it for Beans

		//Ask for an Owner Bean
		//Owner o = ac.getBean(Owner.class);

		//Print out the owner bean (sout)
		//System.out.println(o); //we didn't give it values so it has a null name, and age of 0
		//nothing interesting really happened here, besides the fact that this is a Bean

		//Now let's ask for an Animal Bean
		Animal a = ac.getBean(Animal.class);

		//Print out the animal bean
		System.out.println(a);

		//We got an Animal object... WITH an automatically injected Owner object.
		//Spring saw Bean Wiring in applicationContext.xml and injected accordingly.

		//Let's give the Animal Bean some values
		a.setType("Ladybug");
		a.setName("Luna");
		a.setWeight(1);
		a.setColor("Red");
		//we can get the owner properties
		a.getOwner().setName("Jemal");
		a.getOwner().setAge(24);

		//Print out the animal Bean
		System.out.println(a);

		//Bean Scopes -------------------------------------------

		//Let's try to get a new Animal Bean
		Animal a2 = ac.getBean(Animal.class);

		//Print out the new Animal Bean... It's the same exact animal as previously defined???
		System.out.println(a2);

		//This is due to Beans being SINGLETONS by default.

		//Let's edit the name of animal a2 and then print both animals
		a2.setName("Luna2");
		a2.setWeight(5);
		System.out.println(a);
		System.out.println(a2);

	}

}
