package com.elo7.search.usecases;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.elo7.search.domains.Movie;
import com.elo7.search.domains.SearchQuery;
import com.elo7.search.domains.SearchResult;
import com.elo7.search.gateways.MovieGateway;

@RunWith(SpringJUnit4ClassRunner.class)
public class SearchMoviesTest {

    @Mock
    private MovieGateway movieGateway;

    @InjectMocks
    private SearchMovies searchMovies;

    @Test
    public void test_search_valid_query() {
        final SearchResult searchResult = new SearchResult();
        searchResult.setHits(1l);
        searchResult.addMovie(movie("Alien", new HashSet<>(Arrays.asList( "Horror", "Sci-Fi")), 8.5f, 022f));

        Mockito.when(movieGateway.findByQuery(Mockito.any(SearchQuery.class))).thenReturn(searchResult);

        Assert.assertEquals(1l, searchMovies.search(new SearchQuery("Alien")).getHits().longValue());
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
