package com.stackroute.MovieApp.service;

import com.stackroute.MovieApp.domain.Movie;
import com.stackroute.MovieApp.exception.MovieAlreadyExistsException;
import com.stackroute.MovieApp.exception.MovieNotFoundException;

import java.util.List;

public interface MovieService {
    public Movie saveMovie (Movie movie) throws MovieAlreadyExistsException;
    public List<Movie> getAllMovies() throws MovieNotFoundException;
    public boolean deleteMovie(int id) throws MovieNotFoundException;
    public Movie updateMovie(Movie movie) throws MovieNotFoundException;
    public List<Movie> getMovieByName(String name);

}
