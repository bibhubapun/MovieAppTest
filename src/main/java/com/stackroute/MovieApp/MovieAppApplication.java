package com.stackroute.MovieApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextRefreshedEvent;

@SpringBootApplication
public class MovieAppApplication {

	public static void main(String[] args) {

		SpringApplication.run(MovieAppApplication.class, args);
//		MovieAppStartup startup=new MovieAppStartup();
//		startup.onApplicationEvent();
	}

}
