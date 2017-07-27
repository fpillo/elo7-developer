package com.elo7.search.gateways;


import com.elo7.search.domains.Movie;
import com.elo7.search.domains.SearchQuery;

import java.util.Collection;

public interface MovieGateway {

    Movie save(Movie movie);

    Collection<Movie> findByQuery(SearchQuery searchQuery);

}
