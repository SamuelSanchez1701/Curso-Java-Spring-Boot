package com.platzi.play.persistance;

import com.platzi.play.domain.dto.MovieDto;
import com.platzi.play.domain.dto.UpdateMovieDto;
import com.platzi.play.domain.exception.MovieAlreadyExistsException;
import com.platzi.play.domain.exception.MovieNotFound;
import com.platzi.play.domain.repository.MovieRepository;
import com.platzi.play.persistance.crud.CrudMovieEntity;
import com.platzi.play.persistance.entity.MovieEntity;
import com.platzi.play.persistance.mapper.MovieMapper;
import org.apache.catalina.startup.ListenerCreateRule;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
@Repository
public class MovieEntityRepository implements MovieRepository {
    private final CrudMovieEntity crudMovieEntity;
    private final MovieMapper movieMapper;

    public MovieEntityRepository(CrudMovieEntity crudMovieEntity, MovieMapper movieMapper) {
        this.crudMovieEntity = crudMovieEntity;
        this.movieMapper = movieMapper;
    }

    @Override
    public List<MovieDto> getAll() {
        return this.movieMapper.toDto(this.crudMovieEntity.findAll());
    }

    @Override
    public MovieDto getById(long id) {
        MovieEntity movieEntity = this.crudMovieEntity.findById(id).orElseThrow(MovieNotFound::new);
        return this.movieMapper.toDto(movieEntity);
    }

    @Override
    public MovieDto save(MovieDto movieDto) {
        if (this.crudMovieEntity.findFirstByTitulo(movieDto.title()) != null){
            throw new MovieAlreadyExistsException(movieDto.title());
        }

        MovieEntity movieEntity = this.movieMapper.toEntity(movieDto);
        movieEntity.setEstado("D");

        return this.movieMapper.toDto(this.crudMovieEntity.save(movieEntity));
    }

    @Override
    public MovieDto update(long id, UpdateMovieDto updateMovieDto) {

        MovieEntity movieEntity = this.crudMovieEntity.findById(id).orElseThrow(MovieNotFound::new);

// Acá fue para manejar que no se repitiera el título de la película

        if (!movieEntity.getTitulo().equals(updateMovieDto.title())) {

            MovieEntity existingMovie = this.crudMovieEntity.findFirstByTitulo(updateMovieDto.title());

            if (existingMovie != null) {

                throw new MovieAlreadyExistsException(updateMovieDto.title());

            }

        }

        this.movieMapper.updateEntityFromDto(updateMovieDto, movieEntity);

        return this.movieMapper.toDto(this.crudMovieEntity.save(movieEntity));

    }

    @Override

    public void deleteById(long id) {

        var movieEntity = crudMovieEntity.findById(id).orElseThrow(MovieNotFound::new);

        this.crudMovieEntity.delete(movieEntity);

    }
}