package com.aps.spring.annotation;

import com.aps.spring.xml.FriendClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.util.ArrayList;
import java.util.List;

@PropertySource(value = "application.properties")
@Configuration
public class BeanConfiguration {

    @Value("${conditional.property:false}")
    Boolean conditionalProperty;

    @Qualifier(value = "friendlyBean")
    @Autowired
    private FriendClass friendObj;

    @Bean(name = "helloWorld", initMethod = "init", destroyMethod = "destroy")
    public SpringAnnotationDemo springAnnotationDemo() {
        SpringAnnotationDemo springAnnotationDemo = new SpringAnnotationDemo(friendObj);
        springAnnotationDemo.setMessage("Hello World!");
        return springAnnotationDemo;
    }

    @Bean
    public SpringAnnotationDemo springAnnotationLazyBeanDemo() {
        return new SpringAnnotationDemo(friendClassLazy());
    }

    @Conditional(value = ConditionalBean.class)
    @Bean(name = "friendlyBean")
    public FriendClass friendClass(){
        return new FriendClass();
    }

    @Lazy
    @Bean
    public FriendClass friendClassLazy(){
        System.out.println("\n\n***Using lazy initialized bean***");
        return new FriendClass();
    }

    @Bean
    public List<String> namesList(){
        List<String> list = new ArrayList<>();
        list.add("Aman");
        list.add("Narayan");
        return list;
    }

}
