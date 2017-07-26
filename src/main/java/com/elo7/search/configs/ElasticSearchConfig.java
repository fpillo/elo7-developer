package com.elo7.search.configs;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;

@Configuration
public class ElasticSearchConfig {

    @Bean
    public Client client() throws Exception {
        final Settings settings = Settings.builder()
                .put("cluster.name", "elasticsearch").build();
        final TransportClient client = new PreBuiltTransportClient(settings);
        final TransportAddress address = new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300);
        client.addTransportAddress(address);

        return client;
    }

}
