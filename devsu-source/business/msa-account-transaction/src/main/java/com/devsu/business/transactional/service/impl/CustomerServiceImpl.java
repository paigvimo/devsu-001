package com.devsu.business.transactional.service.impl;

import com.devsu.business.transactional.exception.NotFoundException;
import com.devsu.business.transactional.service.CustomerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    WebClient.Builder webClientBuilder;

    @Override
    public boolean existCustomer(Long customerId) {
        WebClient webClient = webClientBuilder.build();
        return Boolean.TRUE.equals(webClient.get()
                .uri("http://msa-customer/customers/{id}", customerId)
                .retrieve()
                .onStatus(httpStatusCode -> httpStatusCode.value() == 404,
                        clientResponse -> Mono.error(new NotFoundException("CUSTOMER NOT FOUND")))
                .bodyToMono(Void.class)
                .doOnSuccess(response -> log.info("< * CUSTOMER WITH ID {} EXIST * >", customerId))
                .doOnError(response-> log.warn("< * CUSTOMER WITH ID {} NOT EXIST * >", customerId))
                .then(Mono.just(true))
                .onErrorReturn(false)
                .block());
    }
}
