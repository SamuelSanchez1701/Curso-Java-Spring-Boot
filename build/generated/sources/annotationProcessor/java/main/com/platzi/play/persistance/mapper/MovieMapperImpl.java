package com.platzi.play.persistance.mapper;

import com.platzi.play.domain.Genre;
import com.platzi.play.domain.dto.MovieDto;
import com.platzi.play.domain.dto.UpdateMovieDto;
import com.platzi.play.persistance.entity.MovieEntity;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-10-14T14:56:35-0500",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-java-compiler-worker-9.7.1.jar, environment: Java 21.0.12.1 (Microsoft)"
)
@Component
public class MovieMapperImpl implements MovieMapper {

    @Override
    public MovieDto toDto(MovieEntity entity) {
        if ( entity == null ) {
            return null;
        }

        String title = null;
        Integer duration = null;
        Genre genre = null;
        LocalDate releaseDate = null;
        Double rating = null;
        Long id = null;

        title = entity.getTitulo();
        duration = entity.getDuracion();
        genre = GenreMapper.stringToGenre( entity.getGenero() );
        releaseDate = entity.getFechaEstreno();
        if ( entity.getClasificacion() != null ) {
            rating = entity.getClasificacion().doubleValue();
        }
        id = entity.getId();

        Boolean status = null;

        MovieDto movieDto = new MovieDto( id, title, duration, genre, releaseDate, rating, status );

        return movieDto;
    }

    @Override
    public List<MovieDto> toDto(Iterable<MovieEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<MovieDto> list = new ArrayList<MovieDto>();
        for ( MovieEntity movieEntity : entities ) {
            list.add( toDto( movieEntity ) );
        }

        return list;
    }

    @Override
    public MovieEntity toEntity(MovieDto dto) {
        if ( dto == null ) {
            return null;
        }

        MovieEntity movieEntity = new MovieEntity();

        movieEntity.setGenero( GenreMapper.genreToString( dto.genre() ) );
        movieEntity.setTitulo( dto.title() );
        movieEntity.setDuracion( dto.duration() );
        movieEntity.setFechaEstreno( dto.releaseDate() );
        if ( dto.rating() != null ) {
            movieEntity.setClasificacion( BigDecimal.valueOf( dto.rating() ) );
        }

        return movieEntity;
    }

    @Override
    public void updateEntityFromDto(UpdateMovieDto updateMovieDto, MovieEntity movieEntity) {
        if ( updateMovieDto == null ) {
            return;
        }

        movieEntity.setTitulo( updateMovieDto.title() );
        movieEntity.setFechaEstreno( updateMovieDto.releaseDate() );
        if ( updateMovieDto.rating() != null ) {
            movieEntity.setClasificacion( BigDecimal.valueOf( updateMovieDto.rating() ) );
        }
        else {
            movieEntity.setClasificacion( null );
        }
    }
}
