package com.eleks.academy.pharmagator.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class DataProvidersConfig {

    @Value("${pharmagator.data-providers.apteka-ds.url}")
    private String pharmacyDSBaseUrl;

    @Value("${pharmagator.data-providers.apteka-rozetka.url}")
    private String pharmacyRozetkaBaseUrl;

    @Bean
    public WebClient pharmacyDSWebClient() {
        return getWebClientWithDefaultHeadersSetup(pharmacyDSBaseUrl);
    }

    @Bean
    public WebClient pharmacyRozetkaWebClient() {
        return getWebClientWithDefaultHeadersSetup(pharmacyRozetkaBaseUrl);
    }

    private WebClient getWebClientWithDefaultHeadersSetup(String baseUrl) {
        return WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .baseUrl(baseUrl)
                .build();
    }
}
