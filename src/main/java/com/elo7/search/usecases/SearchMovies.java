package com.elo7.search.usecases;

import com.elo7.search.domains.SearchQuery;
import com.elo7.search.domains.SearchResult;
import com.elo7.search.gateways.MovieGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class SearchMovies {

    private final MovieGateway movieGateway;

    @Autowired
    public SearchMovies(final MovieGateway movieGateway) {
        this.movieGateway = movieGateway;
    }

    public Collection<SearchResult> search(final SearchQuery searchQuery) {
        return new ArrayList<>();
    }

}
