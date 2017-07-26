package com.elo7.search.controllers;

import com.elo7.search.domains.SearchMovie;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;

@RestController
public class SearchMovieController {

    @RequestMapping(method = RequestMethod.GET, value = "/movies", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Collection<SearchMovie> searchMovies() {
        return new ArrayList<>();
    }

}
