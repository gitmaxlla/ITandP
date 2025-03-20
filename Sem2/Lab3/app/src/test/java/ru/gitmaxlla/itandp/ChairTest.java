package ru.gitmaxlla.itandp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ChairTest {
    private final static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    @Test
    public void checkTestBean() {
        Bean testBean = context.getBean("TestBean", Bean.class);
        assertEquals("Works", testBean.getName());
    }

    @Test
    public void checkTestTable() {
        TableLombokExample testTable = context.getBean("TestTable", TableLombokExample.class);
        assertEquals(500, testTable.priceFor(5, TableLombokExample.CONDITION.UNSPECIFIED));
    }

    @AfterAll
    public static void shutdown() {
        context.close();
    }
}
