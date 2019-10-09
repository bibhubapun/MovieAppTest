package com.stackroute.MovieApp.service;

import com.stackroute.MovieApp.domain.Movie;
import com.stackroute.MovieApp.exception.MovieAlreadyExistsException;
import com.stackroute.MovieApp.exception.MovieNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Qualifier
public class MovieDummyServiceImpl implements MovieService {
    @Override
    public Movie saveMovie(Movie movie) throws MovieAlreadyExistsException {
        return null;
    }

    @Override
    public List<Movie> getAllMovies() throws MovieNotFoundException {
        return null;
    }

    @Override
    public boolean deleteMovie(int id) throws MovieNotFoundException {
        return false;
    }

    @Override
    public Movie updateMovie(Movie movie) throws MovieNotFoundException {
        return null;
    }

    @Override
    public List<Movie> getMovieByName(String name) {
        return null;
    }
}
