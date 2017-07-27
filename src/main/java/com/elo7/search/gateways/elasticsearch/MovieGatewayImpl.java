package com.elo7.search.gateways.elasticsearch;

import com.elo7.search.domains.Movie;
import com.elo7.search.domains.SearchQuery;
import com.elo7.search.exceptions.SaveMovieException;
import com.elo7.search.gateways.MovieGateway;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
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
    public Movie save(final Movie movie) {
        try {
            final IndexResponse response = client.prepareIndex("search", "movies", movie.getId())
                    .setSource(parse(movie))
                    .get();

            return movie;
        } catch (final Throwable throwable) {
            throw new SaveMovieException();
        }
    }

    @Override
    public Collection<Movie> findByQuery(final SearchQuery searchQuery) {
        return null;
    }

    private XContentBuilder parse(final Movie movie) throws Exception {
        final XContentBuilder builder = XContentFactory
                .jsonBuilder()
                .startObject()
                .field("title", movie.getTitle())
                .field("genres", movie.getGenres())
                .field("grade", movie.getGrade())
                .endObject();

        return builder;
    }

}
