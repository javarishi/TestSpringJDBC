package com.test;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JDBCDemo {

	public static void main(String[] args) {
		AbstractApplicationContext context =
                                new ClassPathXmlApplicationContext("beans.xml");
       
        ActorDAO dao = context.getBean("actorDAO", ActorDAO.class);
        
        // Select
        ActorDTO actor = dao.getActor(2);
        System.out.println(actor.getActorId() + "  " + actor.getFirstName() + " " + actor.getLastName());
        
        // update
        actor.setFirstName("RAM");
        int result = dao.updateActor(actor);
        System.out.println("Number of rows affected :: " + result);
        
        actor = dao.getActor(2);
        System.out.println(actor.getActorId() + "  " + actor.getFirstName() + " " + actor.getLastName()); 
 
        // insert
        ActorDTO newActor = new ActorDTO();
        newActor.setActorId(4999);
        newActor.setFirstName("David");
        newActor.setLastName("Burrow");
        result =  dao.insertActor(newActor);
        System.out.println("Number of rows affected :: " + result);
        // Checking the insert statement
        actor = dao.getActor(4999);
        System.out.println(actor.getActorId() + "  " + actor.getFirstName() + " " + actor.getLastName()); 
 
        context.close();
        
	}
}
