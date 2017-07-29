package com.elo7.search.usecases;


import com.elo7.search.domains.Movie;
import com.elo7.search.exceptions.SaveMovieException;
import com.elo7.search.gateways.MovieGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        validateDomain.validate(movie);
        try {
            return movieGateway.save(movie);
        } catch (final Throwable throwable) {
            throw new SaveMovieException(movie.getId(), throwable);
        }
    }

}
