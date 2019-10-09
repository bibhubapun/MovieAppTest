package com.stackroute.MovieApp.controller;

import com.stackroute.MovieApp.domain.Movie;
import com.stackroute.MovieApp.exception.MovieAlreadyExistsException;
import com.stackroute.MovieApp.exception.MovieNotFoundException;
import com.stackroute.MovieApp.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1")
public class MovieController {
    MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("movie")
    public ResponseEntity<?> saveMovie(@RequestBody Movie movie) {
        ResponseEntity responseEntity;
        try{
            movieService.saveMovie(movie);
            responseEntity=new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);
        }catch (MovieAlreadyExistsException e){
            responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping("movie")
    public ResponseEntity<?> getAllMovies(){
        ResponseEntity responseEntity;
        try {
           responseEntity=new ResponseEntity<List<Movie>>( movieService.getAllMovies(),HttpStatus.OK);
        }catch (MovieNotFoundException e){
            responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);

        }
        return responseEntity;
    }

    @GetMapping("movie/{movieName}")
    public ResponseEntity<?> getMovieByName(@PathVariable ("movieName") String name){
        return  new ResponseEntity<List<Movie>>(movieService.getMovieByName(name), HttpStatus.OK);
    }

    @DeleteMapping("movie/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable("id") int id)
    {
        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity<Boolean>(movieService.deleteMovie(id), HttpStatus.OK);
        }catch (MovieNotFoundException e){
            responseEntity=new ResponseEntity(e.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }


    @RequestMapping(method = RequestMethod.PATCH,value = "movie")
    public ResponseEntity<?> updateMovie(@RequestBody Movie movie)
    { ResponseEntity responseEntity;
       try{
           responseEntity=new ResponseEntity<Movie>(movieService.updateMovie(movie),HttpStatus.OK);
       }catch (MovieNotFoundException e){
           responseEntity=new ResponseEntity(e.getMessage(),HttpStatus.CONFLICT);
       }
        return responseEntity;
    }

}