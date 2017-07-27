package com.elo7.search.domains;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@ToString
public class Movie {

    @NotBlank(message = "movie.id.notBlank")
    private String id;

    @NotBlank(message = "movie.title.notBlank")
    private String title;

    @NotNull(message = "movie.genres.notNull")
    private Set<String> genres;

    @NotNull(message = "movie.grade.notNull")
    private Float grade;

    private Float score;

}
