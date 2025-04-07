package ru.gitmaxlla.itandp;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext javaContext =
                        new AnnotationConfigApplicationContext(SpringConfig.class)) {
            DiningSet cheapSet = javaContext.getBean("CheapDiningSet", DiningSet.class);
            DiningSetAutowired cheapSetAutowired =
                    javaContext.getBean("CheapDiningSetButAutowired", DiningSetAutowired.class);

            System.out.println("java context (autowired) --> " + cheapSetAutowired.getPrice());
            System.out.println("java context --> " + cheapSet.getPrice());
        }

        try (ClassPathXmlApplicationContext xmlContext =
                new ClassPathXmlApplicationContext("applicationContext.xml")) {
            DiningSet cheapSet = xmlContext.getBean("CheapDiningSet", DiningSet.class);
            DiningSetAutowired cheapSetAutowired =
                    xmlContext.getBean("CheapDiningSetButAutowired", DiningSetAutowired.class);

            System.out.println("xml context (autowired) --> " + cheapSetAutowired.getPrice());
            System.out.println("xml context --> " + cheapSet.getPrice());
        }
    }
}
