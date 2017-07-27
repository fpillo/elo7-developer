package com.elo7.search.domains;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@ToString
public class Movie {

    private String id;

    private String title;

    private Set<String> genres;

    private Float grade;

    private Float score;

}
