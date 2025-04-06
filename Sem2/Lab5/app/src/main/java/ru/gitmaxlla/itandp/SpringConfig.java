package ru.gitmaxlla.itandp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class SpringConfig {
	@Bean(name = "CheapDiningSet")
	public DiningSet getCheapDiningSet() {
		HashMap<Furniturable, Integer> contents = new HashMap<>();
		contents.put(getACheapButNiceChair(), 2);
		contents.put(getAVeryCheapTable(), 1);

		return new DiningSet(contents, 0.05);
	}

	@Bean(name = "AVeryCheapTable")
	public Table getAVeryCheapTable() {
		return new Table(150.0, 100.0, 70.0, 10.0, 1.0, 1.0,
				"VeryCheap Table v1.0", Furniturable.CONDITION.STORE_SHELF, false,
				1.0, 2);
	}

	@Bean(name = "ACheapButNiceChair")
	public Chair getACheapButNiceChair() {
		return new Chair(50.0, 50.0, 50.0, 10.0, 0.5, 1.5,
				"PrettyNice Chair v2.0", Furniturable.CONDITION.BRAND_NEW, false,
				3, 0.0);
	}
}
