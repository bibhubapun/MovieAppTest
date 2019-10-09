package com.stackroute.MovieApp.exception;

public class MovieNotFoundException extends Exception {
    private String message;

    public MovieNotFoundException() {
    }

    public MovieNotFoundException(String string){
        super(string);
        this.message=string;
    }
}
