package com.github.fabriciolfj.accountservice.interfaceadapter.provider;

import com.github.fabriciolfj.accountservice.domain.exceptions.ProductClientException;
import com.github.fabriciolfj.accountservice.interfaceadapter.provider.model.GetProductResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.math.BigDecimal;
import java.net.URI;
import java.time.Duration;

@Slf4j
@Component
public class ProductProvider {

    @Autowired
    private WebClient webClient;

    @Value("${app.product}")
    private String url;

    public Mono<GetProductResponse> find(final BigDecimal value, final String customer) {
        return webClient
                .get()
                .uri(URI.create(url + "/api/v1/products/" + value + "/" + customer + "/link"))
                .retrieve()
                .bodyToMono(GetProductResponse.class)
                .timeout(Duration.ofSeconds(2), Mono.empty())
                .onErrorResume(ProductClientException.class, ex -> Mono.empty())
                .retryWhen(Retry.backoff(3, Duration.ofMillis(100)))
                .onErrorResume(ProductClientException.class, ex -> Mono.empty());
    }
}
