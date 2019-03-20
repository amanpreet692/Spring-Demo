package com.aps.spring.annotation;

import com.aps.spring.xml.FriendClass;
import com.aps.spring.xml.SpringXMLDemo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class SpringAnnotationDemo {

    private String message;

    private Integer ctr = 0;

    private final FriendClass friendObject;

    public SpringAnnotationDemo() {
        friendObject = null;
    }

    public SpringAnnotationDemo(FriendClass friendObject) {
        System.out.println(String.format("%s\nMy life starts here :)",this));
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
        ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfiguration.class);
        ((AnnotationConfigApplicationContext) context).registerShutdownHook();

        //Scope Attribute Demo
        System.out.println("\n\n**** Scope Attribute Demo ****");
        System.out.println("Singleton:");
        SpringAnnotationDemo obj = (SpringAnnotationDemo) context.getBean("helloWorld");
        System.out.println("helloWorld: " + obj.getCtr());
        obj = (SpringAnnotationDemo) context.getBean("helloWorld");
        System.out.println("helloWorld: " + obj.getCtr());

        System.out.println("\nPrototype:");
        obj = (SpringAnnotationDemo) context.getBean("helloWorldProto");
        System.out.println("helloWorldProto: " + obj.getCtr());
        obj = (SpringAnnotationDemo) context.getBean("helloWorldProto");
        System.out.println("helloWorldProto: " + obj.getCtr());

        //Collections Demo
        System.out.println("\n\n**** Collections Demo****");
        List namesList = (List)context.getBean("namesList");
        System.out.println(namesList);
    }

}
