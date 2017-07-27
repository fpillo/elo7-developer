package com.elo7.search.domains;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
public class SearchResult {

    private Long hits;

    private Collection<Movie> movies = new ArrayList<>();

    public void addMovie(final Movie movie) {
        this.movies.add(movie);
    }

}
