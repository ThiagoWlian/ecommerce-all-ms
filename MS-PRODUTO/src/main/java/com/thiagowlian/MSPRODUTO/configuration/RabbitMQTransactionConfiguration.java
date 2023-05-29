package com.thiagowlian.MSPRODUTO.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.thiagowlian.MSPRODUTO.messageBroker.FilasMensageria.*;

@Slf4j
@Configuration
public class RabbitMQTransactionConfiguration {

    @Bean
    public DirectExchange exchangeProducoReducao() {
        return new DirectExchange(VENDA_REALIZADA_PRODUDO_REDUCAO_ESTOQUE_EXCHANGE);
    }

    @Bean
    public Queue queueProdutoReduzirEstoque() {
        log.info("Gerando fila: " + VENDA_REALIZADA_PRODUTO_REDUZIR_ESTOQUE_EXCHANGE);
        return new Queue(VENDA_REALIZADA_PRODUTO_REDUZIR_ESTOQUE_EXCHANGE);
    }

    @Bean
    public Queue queueVendaFeedbackErroTransaction() {
        log.info("Gerando fila: " + REALIZAR_VENDA_FEEDBACK_ERRO_TRANSACTION);
        return new Queue(REALIZAR_VENDA_FEEDBACK_ERRO_TRANSACTION);
    }

    @Bean
    public Binding bindingProdutoReduzirEstoque() {
        return BindingBuilder
                .bind(new Queue(VENDA_REALIZADA_PRODUTO_REDUZIR_ESTOQUE_EXCHANGE))
                .to(new FanoutExchange(VENDA_REALIZADA_EXCHANGE));
    }

    @Bean
    public Binding bindingVendaFeedback() {
        return BindingBuilder
                .bind(new Queue(REALIZAR_VENDA_FEEDBACK_ERRO_TRANSACTION))
                .to(new DirectExchange(VENDA_FEEDBACK_EXCHANGE))
                .with(VENDA_FEEDBACK_ERRO_ROUTING_KEY);
    }
}
