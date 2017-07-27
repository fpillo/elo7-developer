package com.elo7.search.gateways.elasticsearch;

import com.elo7.search.domains.Movie;
import com.elo7.search.domains.SearchQuery;
import com.elo7.search.gateways.MovieGateway;
import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class MovieGatewayImpl implements MovieGateway {

    private final Client client;

    @Autowired
    public MovieGatewayImpl(final Client client) {
        this.client = client;
    }

    @Override
    public Collection<Movie> findByQuery(final SearchQuery searchQuery) {
        return null;
    }
}
