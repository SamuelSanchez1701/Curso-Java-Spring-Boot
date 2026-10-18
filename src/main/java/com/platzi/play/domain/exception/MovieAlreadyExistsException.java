package com.platzi.play.domain.exception;

import com.platzi.play.persistance.mapper.MovieMapper;

public class MovieAlreadyExistsException extends RuntimeException{
    public MovieAlreadyExistsException (String movieTitle){
        super("La pelicula " + movieTitle + "ya existe.");
    }
}
