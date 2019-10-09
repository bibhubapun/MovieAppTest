package com.stackroute.MovieApp.exception;

public class MovieAlreadyExistsException extends Exception{
    private String message;

    public MovieAlreadyExistsException() {
    }

    public MovieAlreadyExistsException(String string){
        super(string);
        this.message=string;
    }
}
