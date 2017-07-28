package com.elo7.search.usecases;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.validation.Validation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.elo7.search.domains.Movie;
import com.elo7.search.domains.SearchQuery;
import com.elo7.search.domains.SearchResult;
import com.elo7.search.gateways.MovieGateway;

@RunWith(SpringJUnit4ClassRunner.class)
public class SearchMoviesTest {

    private MovieGateway movieGateway;

    private ValidateDomain validateDomain;

    private SearchMovies searchMovies;

    @Before
    public void setUp() {
        movieGateway = Mockito.mock(MovieGateway.class);
        validateDomain = new ValidateDomain(Validation.buildDefaultValidatorFactory().getValidator());

        searchMovies = new SearchMovies(movieGateway, validateDomain);
    }

    @Test
    public void test_search_valid_query() {
        final SearchResult searchResult = new SearchResult();
        searchResult.setHits(1l);
        searchResult.addMovie(movie("Alien", new HashSet<>(Arrays.asList( "Horror", "Sci-Fi")), 8.5f, 022f));

        Mockito.when(movieGateway.findByQuery(Mockito.any(SearchQuery.class))).thenReturn(searchResult);

        Assert.assertEquals(1l, searchMovies.search(new SearchQuery("Alien")).getHits().longValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_search_null_query() {
        searchMovies.search(null);
    }

    @Test(expected = RuntimeException.class)
    public void test_search_invalid_query() {
        searchMovies.search(new SearchQuery(null));
    }


    private Movie movie(final String title, final Set<String> genres, final Float grade, final Float score) {
        final Movie movie = new Movie();
        movie.setId(UUID.randomUUID().toString());
        movie.setTitle(title);
        movie.setGenres(genres);
        movie.setGrade(grade);
        movie.setScore(score);

        return movie;

    }


}
