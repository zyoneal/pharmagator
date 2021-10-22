package com.eleks.academy.pharmagator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< HEAD
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
=======
import org.springframework.context.annotation.Bean;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication

>>>>>>> development
@EnableScheduling
public class PharmagatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(PharmagatorApplication.class, args);
    }

<<<<<<< HEAD
=======
    @Bean
    public ProjectionFactory projectionFactory() {
        return new SpelAwareProxyProjectionFactory();
    }

>>>>>>> development
}
