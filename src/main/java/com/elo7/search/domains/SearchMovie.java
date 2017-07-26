package com.elo7.search.domains;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
public class SearchMovie {

    private Integer hits;

    private Collection<Movie> movies;

}
