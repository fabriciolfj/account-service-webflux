package com.github.fabriciolfj.accountservice.interfaceadapter.provider.rate.http;

import com.github.fabriciolfj.accountservice.domain.exceptions.ProductClientException;
import com.github.fabriciolfj.accountservice.interfaceadapter.provider.rate.http.model.GetRateRequest;
import com.github.fabriciolfj.accountservice.interfaceadapter.provider.rate.http.model.GetRateResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.net.URI;
import java.time.Duration;

@Slf4j
@Component
public class RateProvider {

    @Autowired
    private WebClient webClient;

    @Value("${app.product}")
    private String url;

    public Mono<GetRateResponse> find(final GetRateRequest request) {
        return webClient
                .put()
                .uri(URI.create(url + "/api/v1/rulerates"))
                .body(BodyInserters.fromValue(request))
                .retrieve()
                .bodyToMono(GetRateResponse.class)
                .timeout(Duration.ofSeconds(2), Mono.empty())
                .onErrorResume(ProductClientException.class, ex -> Mono.empty())
                .retryWhen(Retry.backoff(3, Duration.ofMillis(100)))
                .onErrorMap(e -> new ProductClientException(e.getMessage()));
    }
}
