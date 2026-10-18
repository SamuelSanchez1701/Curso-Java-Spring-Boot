package com.platzi.play.persistance.crud;

import com.platzi.play.persistance.entity.MovieEntity;
import org.springframework.data.repository.CrudRepository;

public interface CrudMovieEntity extends CrudRepository<MovieEntity, Long> {
    MovieEntity findFirstByTitulo (String Titulo);
}
