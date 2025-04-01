package ru.gitmaxlla.itandp;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.Map;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext javaContext =
                        new AnnotationConfigApplicationContext(SpringConfig.class)) {
            Chair defaultChair = javaContext.getBean("DefaultChair", Chair.class);
            Bed niceBed = javaContext.getBean("NiceBed", Bed.class);

            DiningSet coolSet = new DiningSet(Arrays.asList(
                    Map.entry(defaultChair, 2),
                    Map.entry(niceBed, 1)
            ), 0.05);

            System.out.println(coolSet.getPrice());
        }
    }
}
