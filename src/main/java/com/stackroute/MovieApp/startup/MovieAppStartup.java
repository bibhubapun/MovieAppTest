//package com.stackroute.MovieApp.startup;
//
//import com.stackroute.MovieApp.domain.Movie;
//import com.stackroute.MovieApp.repository.MovieRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MovieAppStartup implements ApplicationListener<ContextRefreshedEvent> {
//    private MovieRepository movieRepository;
//
//    @Autowired
//    public MovieAppStartup(MovieRepository movieRepository) {
//        this.movieRepository=movieRepository;
//    }
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//        Movie movie1, movie2;
//        movie1 = new Movie(1, "Inception", "Time Travel", 2010);
//        movie2 = new Movie(2, "Fargo", "Revolution", 2015);
//        movieRepository.save(movie1);
//        movieRepository.save(movie2);
//
//    }
//}
