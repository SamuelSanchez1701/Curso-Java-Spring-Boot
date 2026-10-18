package com.platzi.play.domain.dto;

import com.platzi.play.domain.Genre;
import jakarta.validation.constraints.*;
import org.mapstruct.Mapping;

import java.time.LocalDate;


    public record MovieDto(
            Long id,
            @NotBlank(message = "El titulo es obligatorio")
            String title,
            @Positive(message = "La duracion de la pelicula debe ser mayor que 0")
            Integer duration,
            @NotNull(message = "El género de la pelicula debe ser informado")
            Genre genre,
            @PastOrPresent(message = "La fecha de lanzamiento debe ser anterior a la fecha actual")
            LocalDate releaseDate,
            @Min(value = 0, message = "El rating no puede ser menor que 0")
            @Max(value = 5, message = "El rating no puede ser mayor que 5")
            Double rating,
            @NotNull(message = "El estado debe ser informado")
            Boolean status
    ){

    }

