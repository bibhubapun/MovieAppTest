package com.stackroute.MovieApp.service;

import com.stackroute.MovieApp.domain.Movie;
import com.stackroute.MovieApp.exception.MovieAlreadyExistsException;
import com.stackroute.MovieApp.exception.MovieNotFoundException;
import com.stackroute.MovieApp.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@ConfigurationProperties("application.properties")
@Primary
@Qualifier
public class MovieServiceImpl implements MovieService, ApplicationListener<ApplicationReadyEvent>, CommandLineRunner {

    @Autowired
    Environment environment;
    @Value("${id:default}")
    int id;
    @Value("${movieName:default}")
    String movieName;
    @Value("${plot:default}")
    String plot;
    @Value("${releaseYear:default}")
    int releaseYear;




     private MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Profile("dev")
    @Bean
    public boolean saveTest1(){
        movieRepository.save(new Movie(id,movieName,plot,releaseYear));
        return true;
    }

    @Profile("prod")
    @Bean
    public boolean saveTest2(){
        movieRepository.save(new Movie(id,movieName,plot,releaseYear));
        return true;
    }

    @Override
    public Movie saveMovie(Movie movie) throws MovieAlreadyExistsException {
           if(movieRepository.existsById(movie.getId())){
               throw new MovieAlreadyExistsException("Movie Already Exists");
           }
            Movie savedMovie = movieRepository.save(movie);
            return savedMovie;

    }

    @Override
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    @Override
    public boolean deleteMovie(int id) throws MovieNotFoundException{
        if(!movieRepository.existsById(id)){
            throw new MovieNotFoundException("Movie to be deleted not found");
        }
        else{
        movieRepository.deleteById(id);
        return true;
        }
    }

    @Override
    public Movie updateMovie(Movie movie) throws MovieNotFoundException{
        if(movieRepository.existsById(movie.getId())){
            throw new MovieNotFoundException("Movie to be updated not found");
        }
        Movie updatedMovie=movieRepository.getOne(movie.getId());
        updatedMovie.setMovieName(movie.getMovieName());
        updatedMovie.setPlot(movie.getPlot());
        updatedMovie.setReleaseYear(movie.getReleaseYear());
        return movieRepository.save(updatedMovie);
    }

    @Override
    public List<Movie> getMovieByName(String name) {
      return movieRepository.getMovieByName(name);
    }

    @Override
    public void run(String... args) throws Exception {

    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        Movie movie= new Movie(1,environment.getProperty("movieName"),plot,releaseYear);
        movieRepository.save(movie);
    }
}
