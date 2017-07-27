package com.elo7.search.usecases;

import com.elo7.search.domains.SearchQuery;
import com.elo7.search.domains.SearchResult;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class SearchMovies {

    public Collection<SearchResult> search(final SearchQuery searchQuery) {
        return null;
    }

}
