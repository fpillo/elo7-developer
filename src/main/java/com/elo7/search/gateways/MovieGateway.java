package com.elo7.search.gateways;


import com.elo7.search.domains.Movie;
import com.elo7.search.domains.SearchQuery;
import com.elo7.search.domains.SearchResult;

public interface MovieGateway {

    Movie save(Movie movie) throws Exception;

    SearchResult findByQuery(SearchQuery searchQuery);

}
