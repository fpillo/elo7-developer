package com.elo7.search.usecases;


import com.elo7.search.domains.Movie;
import com.elo7.search.exceptions.SaveMovieException;
import com.elo7.search.gateways.MovieGateway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.Validation;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
public class SaveMovieTest {

    private MovieGateway movieGateway;

    private ValidateDomain validateDomain;

    private SaveMovie saveMovie;

    @Before
    public void setUp() {
        movieGateway = Mockito.mock(MovieGateway.class);
        validateDomain = new ValidateDomain(Validation.buildDefaultValidatorFactory().getValidator());

        saveMovie = new SaveMovie(movieGateway, validateDomain);
    }

    @Test
    public void test_save_valid_movie() {
        saveMovie.save(movie("Alien", new HashSet<>(Arrays.asList( "Horror", "Sci-Fi")), 8.5f));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_save_null_movie() {
        saveMovie.save(null);
    }


    @Test(expected = RuntimeException.class)
    public void test_save_invalid_movie() {
        saveMovie.save(movie("Predator", new HashSet<>(Arrays.asList( "Action", "Sci-Fi", "Thriller")), null));
    }

    @Test(expected = SaveMovieException.class)
    public void test_save_movie_gateway_fail() throws Exception {
        Mockito.when(movieGateway.save(Mockito.any(Movie.class))).thenThrow(IOException.class);
        saveMovie.save(movie("Predator", new HashSet<>(Arrays.asList( "Action", "Sci-Fi", "Thriller")), 9.1f));
    }

    private Movie movie(final String name, final Set<String> genres, final Float grade) {
        final Movie movie = new Movie();
        movie.setId(UUID.randomUUID().toString());
        movie.setTitle(name);
        movie.setGenres(genres);
        movie.setGrade(grade);

        return movie;
    }

}
