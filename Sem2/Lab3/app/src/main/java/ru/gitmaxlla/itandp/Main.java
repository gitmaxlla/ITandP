package ru.gitmaxlla.itandp;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
		AnnotationConfigApplicationContext javaContext = new AnnotationConfigApplicationContext(SpringConfig.class);

		Bean javaBean = javaContext.getBean("Bean", Bean.class);
		System.out.println(javaBean.getName());

		Chair defaultChair = javaContext.getBean("DefaultChair", Chair.class);
		System.out.println(defaultChair.getName());

		Bed niceBed = javaContext.getBean("NiceBed", Bed.class);
		System.out.println(niceBed.getDefaultPillowHeight());

		javaContext.close();

		try (ClassPathXmlApplicationContext xmlContext = new ClassPathXmlApplicationContext("applicationContext.xml");) {
			Bean xmlBean = xmlContext.getBean("Bean", Bean.class);
			System.out.println(xmlBean.getName());

			TableLombokExample xmlTable = xmlContext.getBean("EpicTable", TableLombokExample.class);
			System.out.println(xmlTable.describeUseCase());

			Furniture xmlFurniture1 = xmlContext.getBean("FurnitureItemOne", Furniture.class);
			System.out.println(xmlFurniture1.getCondition());

			Furniture xmlFurniture2 = xmlContext.getBean("FurnitureItemOne", Furniture.class);
			System.out.println(xmlFurniture2.getImmobilityScore());
		}
    }
}
