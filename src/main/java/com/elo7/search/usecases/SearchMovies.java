package com.elo7.search.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.elo7.search.domains.SearchQuery;
import com.elo7.search.domains.SearchResult;
import com.elo7.search.gateways.MovieGateway;

@Component
public class SearchMovies {

    private final MovieGateway movieGateway;

    private final ValidateDomain validateDomain;

    @Autowired
    public SearchMovies(final MovieGateway movieGateway, final ValidateDomain validateDomain) {
        this.movieGateway = movieGateway;
        this.validateDomain = validateDomain;
    }

    public SearchResult search(final SearchQuery searchQuery) {
        validateDomain.validate(searchQuery);
        return movieGateway.findByQuery(searchQuery);
    }

}
