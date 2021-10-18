package com.eleks.academy.pharmagator.config;

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

    @Value("${pharmagator.data-providers.pharmacy-anc.url}")
    private String pharmacyANCBaseUrl;

    @Value("${pharmagator.data-providers.apteka-liki24.url}")
    private String pharmacyLiki24BaseUrl;

    @Value("${pharmagator.data-providers.apteka-rozetka.url}")
    private String pharmacyRozetkaBaseUrl;

    @Value("${pharmagator.data-providers.apteka-alteia.url}")
    private String pharmacyAlteiaBaseUrl;

    @Bean
    public WebClient pharmacyAlteiaWebClient() {
        return WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.TEXT_HTML_VALUE)
                .baseUrl(pharmacyAlteiaBaseUrl)
                .build();
    }

    @Bean(name = "pharmacyDSWebClient")
    public WebClient pharmacyDSWebClient() {
        return WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .baseUrl(pharmacyDSBaseUrl)
                .build();
    }

    @Bean(name = "pharmacyANCWebClient")
    public WebClient pharmacyANCWebClient() {
        return WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .baseUrl(pharmacyANCBaseUrl)
                .build();
    }

    @Bean(name = "pharmacyLiki24WebClient")
    public WebClient pharmacyLiki24WebClient() {
        return WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("x-lang", "uk")
                .baseUrl(pharmacyLiki24BaseUrl)
                .build();
    }

    @Bean(name = "pharmacyRozetkaWebClient")
    public WebClient pharmacyRozetkaWebClient() {
        return WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .baseUrl(pharmacyRozetkaBaseUrl)
                .build();
    }

}
