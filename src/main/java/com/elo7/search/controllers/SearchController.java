package com.elo7.search.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elo7.search.domains.SearchQuery;
import com.elo7.search.domains.SearchResult;
import com.elo7.search.usecases.SearchMovies;

@RestController
public class SearchController {

    private final SearchMovies searchMovies;

    @Autowired
    public SearchController(final SearchMovies searchMovies) {
        this.searchMovies = searchMovies;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search/movies", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SearchResult search(@RequestParam final String q) {
        final SearchQuery searchQuery = new SearchQuery(q);
        return searchMovies.search(searchQuery);
    }

}
