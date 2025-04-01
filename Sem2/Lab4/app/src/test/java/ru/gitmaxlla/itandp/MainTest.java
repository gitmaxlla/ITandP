package ru.gitmaxlla.itandp;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.List;
import java.util.Map;


public class MainTest {
    private final static ClassPathXmlApplicationContext context =
            new ClassPathXmlApplicationContext("applicationContext.xml");

    @Test
    public void checkTestTable() {
        Table testTable = context.getBean("TestTable", Table.class);

        DiningSet fiveTables = new DiningSet(List.of(
                Map.entry(testTable, 5)
        ), 0.5);

        assertNotEquals(0, fiveTables.getPrice());
        assertEquals(250, fiveTables.getPrice());
    }

    @AfterAll
    public static void shutdown() {
        context.close();
    }
}
