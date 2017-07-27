package com.elo7.search.controllers;

import com.elo7.search.domains.SearchQuery;
import com.elo7.search.domains.SearchResult;
import com.elo7.search.usecases.SearchMovies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class SearchController {

    private final SearchMovies searchMovies;

    @Autowired
    public SearchController(final SearchMovies searchMovies) {
        this.searchMovies = searchMovies;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/movies", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Collection<SearchResult> search(@RequestBody final SearchQuery searchQuery) {
        return searchMovies.search(searchQuery);
    }

}
