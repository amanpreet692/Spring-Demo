package com.aps.spring.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class SpringXMLDemo {

    private String message;

    private Integer ctr = 0;

    private final FriendClass friendObject;

    public SpringXMLDemo() {
        friendObject = null;
    }

    public SpringXMLDemo(FriendClass friendObject) {
        System.out.println(String.format("\n\n%s\nMy life starts here :)",this));
        this.friendObject = friendObject;
        System.out.println(this.friendObject);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCtr() {
        return ++ctr;
    }

    public String getMessage() {
        return String.format("Your message is: %s", message);
    }

    //init method
    public void init() {
        System.out.println("\n**** init method ****");
        System.out.println(String.format("Message field is set: %s",message));
    }

    //destroy method
    public void destroy() {
        System.out.println("\n**** destroy method ****");
        System.out.println(String.format("%s\nMy story ends here :(",this));
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        ((ClassPathXmlApplicationContext) context).registerShutdownHook();

        SpringXMLDemo obj = (SpringXMLDemo) context.getBean("helloWorld");

        //Scope Attribute Demo
        System.out.println("\n\n**** Scope Attribute Demo ****");
        System.out.println("Singleton:");
        System.out.println("helloWorld: " + obj.getCtr());
        obj = (SpringXMLDemo) context.getBean("helloWorld");
        System.out.println("helloWorld: " + obj.getCtr());

        System.out.println("\nPrototype:");
        obj = (SpringXMLDemo) context.getBean("helloWorldProto");
        System.out.println("helloWorldProto: " + obj.getCtr());
        obj = (SpringXMLDemo) context.getBean("helloWorldProto");
        System.out.println("helloWorldProto: " + obj.getCtr());

        System.out.println("\n\n**** Collections Demo****");
        List namesList = (List)context.getBean("namesList");
        System.out.println(namesList);
    }

}
