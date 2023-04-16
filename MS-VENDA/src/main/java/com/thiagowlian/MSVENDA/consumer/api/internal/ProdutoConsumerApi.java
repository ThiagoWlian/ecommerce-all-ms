package com.thiagowlian.MSVENDA.consumer.api.internal;

import com.thiagowlian.MSVENDA.dto.ReducaoEstoqueDto;
import com.thiagowlian.MSVENDA.dto.ResponseMessageTo;
import com.thiagowlian.MSVENDA.messageProducer.ProdutoMessageProducer;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;

import java.util.List;

@Component
public class ProdutoConsumerApi {

    @Autowired
    private ProdutoMessageProducer produtoMessageProducer;

    @CircuitBreaker(name = "estoqueReducao", fallbackMethod = "fallback")
    public ResponseEntity consumerEstoqueProduto(List<ReducaoEstoqueDto> ids) {
         ResponseEntity responseEntity = WebClient.create()
                .patch()
                .uri("http://localhost:8080/produto/reduzirEstoque")
                .bodyValue(ids)
                .retrieve()
                .toEntity(ResponseEntity.class)
                .block();

        return responseEntity;
    }

    public ResponseEntity fallback(WebClientRequestException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessageTo("O sistema de produtos está offline! Tente novamente mais tarde."));
    }
}
