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

    @Value("${pharmagator.data-providers.apteka-rozetka.url}")
    private String pharmacyRozetkaBaseUrl;

    @Value("${pharmagator.data-providers.apteka-alteia.url}")
    private String pharmacyAlteiaBaseUrl;

    @Bean
    public WebClient pharmacyDSWebClient() {
        return getWebClientWithDefaultJsonHeadersSetup(pharmacyDSBaseUrl);
    }

    @Bean
    public WebClient pharmacyRozetkaWebClient() {
        return getWebClientWithDefaultJsonHeadersSetup(pharmacyRozetkaBaseUrl);
    }

    @Bean
    public WebClient pharmacyAlteiaWebClient() {
        return getWebRequestWithDefaultHtmlHeadersSetup(pharmacyAlteiaBaseUrl);
    }

    private WebClient getWebClientWithDefaultJsonHeadersSetup(String baseUrl) {
        return WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .baseUrl(baseUrl)
                .build();
    }

    private WebClient getWebRequestWithDefaultHtmlHeadersSetup(String baseUrl) {
        return WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.TEXT_HTML_VALUE)
                .baseUrl(baseUrl)
                .build();
    }
}
