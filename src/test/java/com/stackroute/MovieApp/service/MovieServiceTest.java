package com.stackroute.MovieApp.service;

import com.stackroute.MovieApp.domain.Movie;
import com.stackroute.MovieApp.exception.MovieAlreadyExistsException;
import com.stackroute.MovieApp.exception.MovieNotFoundException;
import com.stackroute.MovieApp.repository.MovieRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class MovieServiceTest {
    Movie movie;

    @Mock
    MovieRepository movieRepository;

    @InjectMocks
    MovieServiceImpl movieService;
    List<Movie> list = null;

    @Before
    public void setUp() {
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        movie = new Movie();
        movie.setId(98);
        movie.setMovieName("qwe");
        movie.setPlot("psychological thriller");
        movie.setReleaseYear(2019);
        list = new ArrayList<>();
        list.add(movie);
    }

    @Test
    public void saveUserTestSuccess() throws MovieAlreadyExistsException {

        when(movieRepository.save((Movie) any())).thenReturn(movie);
        Movie savedUser = movieService.saveMovie(movie);
        Assert.assertEquals(movie, savedUser);

        //verify here verifies that userRepository save method is only called once
        verify(movieRepository, times(1)).save(movie);
    }

    //    @Test(expected = MovieAlreadyExistsException.class)
    @Test
    public void saveUserTestFailure() throws MovieAlreadyExistsException {
        when(movieRepository.save((Movie) any())).thenReturn(null);
        Movie savedUser = movieService.saveMovie(movie);
        System.out.println("savedMovie" + savedUser);
//        Assert.assertNotEquals(movie, savedUser);
//
//        doThrow(new MovieAlreadyExistsException()).when(movieRepository).findById(eq(98));
//        movieService.saveMovie(movie);


    }

    @Test
    public void getAllUser(){
        when(movieRepository.save(movie)).thenReturn(movie);
        System.out.println(movieRepository.save(movie));
        movieRepository.save(movie);
        //stubbing the mock to return specific data
        when(movieRepository.findAll()).thenReturn(list);
        List<Movie> userList = movieService.getAllMovies();
        System.out.println(list.toString());
        System.out.println("###" + userList);
        Assert.assertEquals(list, userList);
    }

    @Test
    public void deleteMovie(){
        movieRepository.save(movie);
        when(movieRepository.save((Movie) any())).thenReturn(movie);

        movieRepository.deleteById(movie.getId());
        List<Movie> list = movieRepository.findAll();

        Assert.assertEquals(true,list.isEmpty());
    }

    @Test
    public void UpdateMovie() throws MovieNotFoundException {
        Movie updatedMovie=null;
        when(movieRepository.save((Movie)any())).thenReturn(movie);
        when(movieRepository.getOne(movie.getId())).thenReturn(movie);
        updatedMovie = movieService.updateMovie(movie);
        Assert.assertEquals(movie,updatedMovie);
        //verify here verifies that userRepository save method is only called once
        verify(movieRepository,times(1)).save(movie);

    }

}
