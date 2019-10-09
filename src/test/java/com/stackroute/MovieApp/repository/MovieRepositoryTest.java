package com.stackroute.MovieApp.repository;

import com.stackroute.MovieApp.domain.Movie;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MovieRepositoryTest {
    @Autowired
    MovieRepository movieRepository;
    Movie movie;

    @Before
    public void setUp()
    {
        movie=new Movie();
        movie.setId(1);
        movie.setMovieName("Dangal");
        movie.setPlot("Wrestling");
        movie.setReleaseYear(2017);
    }

    @After
    public void tearDown(){

        movieRepository.deleteAll();
    }

    @Test
    public void testSaveMovie(){
        movieRepository.save(movie);
        Movie fetchUser = movieRepository.findById(movie.getId()).get();
        Assert.assertEquals(1,fetchUser.getId());

    }

    @Test
    public void testSaveUserFailure(){
        Movie testUser = new Movie(3,"dabbang","Herogiri",2010);
        movieRepository.save(movie);
        Movie fetchUser = movieRepository.findById(movie.getId()).get();
        Assert.assertNotSame(testUser,movie);
    }

    @Test
    public void testGetAllUser(){
        Movie u = new Movie(1,"Johny","Jenny",1110);
        Movie u1 = new Movie(2,"Jenny","Harry12",2013);
        movieRepository.save(u);
        movieRepository.save(u1);

        List<Movie> list = movieRepository.findAll();
        Assert.assertEquals("Johny",list.get(0).getMovieName());

    }

    @Test
    public void testDeleteMovie(){
        movieRepository.save(movie);

        movieRepository.deleteById(movie.getId());
        List<Movie> list = movieRepository.findAll();

        Assert.assertEquals(true,list.isEmpty());

    }

    @Test
    public void testUpdateMovie(){
        movieRepository.save(movie);
        Movie updateMovie = movieRepository.findById(movie.getId()).get();
        updateMovie.setPlot("Wrestling1");
        movieRepository.save(updateMovie);
        Assert.assertEquals("Wrestling1",updateMovie.getPlot());
    }

}
