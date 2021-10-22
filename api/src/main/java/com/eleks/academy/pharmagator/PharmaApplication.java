package com.eleks.academy.pharmagator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
<<<<<<< HEAD:api/src/main/java/com/eleks/academy/pharmagator/PharmaApplication.java
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
public class PharmaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PharmaApplication.class, args);
	}
=======
@EnableScheduling
public class PharmagatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(PharmagatorApplication.class, args);
    }
>>>>>>> parent/main:api/src/main/java/com/eleks/academy/pharmagator/PharmagatorApplication.java

    @Bean
    public ProjectionFactory projectionFactory() {
        return new SpelAwareProxyProjectionFactory();
    }

}
