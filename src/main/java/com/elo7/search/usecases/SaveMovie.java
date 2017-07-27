package com.elo7.search.usecases;


import com.elo7.search.domains.Movie;
import com.elo7.search.gateways.MovieGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SaveMovie {

    private final MovieGateway movieGateway;

    private final ValidateDomain validateDomain;

    @Autowired
    public SaveMovie(final MovieGateway movieGateway, final ValidateDomain validateDomain) {
        this.movieGateway = movieGateway;
        this.validateDomain = validateDomain;
    }

    public Movie save(final Movie movie) {
        setUUID(movie);
        validateDomain.validate(movie);
        return movieGateway.save(movie);
    }

    private void setUUID(final Movie movie) {
        movie.setId(UUID.randomUUID().toString());
    }

}
