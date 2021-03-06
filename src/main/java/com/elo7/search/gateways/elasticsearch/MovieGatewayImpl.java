package com.elo7.search.gateways.elasticsearch;

import com.elo7.search.domains.Movie;
import com.elo7.search.domains.SearchQuery;
import com.elo7.search.domains.SearchResult;
import com.elo7.search.gateways.MovieGateway;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.lucene.search.function.CombineFunction;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;

@Component
public class MovieGatewayImpl implements MovieGateway {

    private final Client client;

    @Autowired
    public MovieGatewayImpl(final Client client) {
        this.client = client;
    }

    @Override
    public Movie save(final Movie movie) throws Exception {
        final IndexResponse response = client.prepareIndex("search", "movies", movie.getId())
                .setSource(parse(movie))
                .get();

        return movie;
    }

    @Override
    public SearchResult findByQuery(final SearchQuery searchQuery) {
        final SearchResponse response = client.prepareSearch("search")
                .setTypes("movies")
                .setQuery(createQueryBySearchQuery(searchQuery))
                .setTimeout(TimeValue.timeValueMillis(2000))
                .get();

        return createResult(response.getHits());
    }

    private QueryBuilder createQueryBySearchQuery(final SearchQuery searchQuery) {
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("title", searchQuery.getQ());
        if (StringUtils.isEmpty(searchQuery.getQ())) {
            queryBuilder = QueryBuilders.matchAllQuery();
        }

        return QueryBuilders
                .functionScoreQuery(
                    queryBuilder,
                    ScoreFunctionBuilders.fieldValueFactorFunction("grade"))
                .boostMode(CombineFunction.SUM);
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

    private SearchResult createResult(final SearchHits hits) {
        final SearchResult searchResult = new SearchResult();
        searchResult.setHits(hits.totalHits);
        Arrays.stream(hits.getHits()).forEach(hit -> {
            try {
                searchResult.addMovie(parseHit(hit));
            } catch (final Throwable t) {
                //TODO: log
            }
        });

        return searchResult;
    }

    private Movie parseHit(final SearchHit hit) throws Exception {
        final ObjectMapper mapper = new ObjectMapper();
        final Movie movie = mapper.readValue(hit.getSourceAsString(), Movie.class);
        movie.setId(hit.getId());
        movie.setScore(hit.getScore());

        return movie;
    }

}
