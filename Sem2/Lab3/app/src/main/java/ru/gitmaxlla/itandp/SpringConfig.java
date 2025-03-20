package ru.gitmaxlla.itandp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
	@Bean(name = "Bean")
	public ru.gitmaxlla.itandp.Bean getBean() {
		return new ru.gitmaxlla.itandp.Bean("This is a Java Bean");
	}

	@Bean(name = "DefaultChair")
	public ru.gitmaxlla.itandp.Chair getDefaultChair() {
		return new Chair(60.0, 160.0, 50.0, 3000, 15, 70, "A chair", Chair.CONDITION.STORE_SHELF, true, 3, 50.0);
	}

	@Bean(name = "NiceBed")
	public ru.gitmaxlla.itandp.Bed getNiceBed() {
		return new ru.gitmaxlla.itandp.Bed(70.0, 30.0, 170.0, 10000, 90, 50, "A nice bed", Bed.CONDITION.BRAND_NEW, false, false, 12.0);
	}
}
