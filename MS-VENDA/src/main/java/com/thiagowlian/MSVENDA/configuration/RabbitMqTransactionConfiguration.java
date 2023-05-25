package com.thiagowlian.MSVENDA.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.thiagowlian.MSVENDA.messageBroker.FilasMensageria.*;

@Slf4j
@Configuration
public class RabbitMqTransactionConfiguration {
    @Bean
    public FanoutExchange fanoutExchangeVendaRealizada() {
        log.info("Gerando exchange: " + VENDA_REALIZADA_EXCHANGE);
        return new FanoutExchange(VENDA_REALIZADA_EXCHANGE);
    }

    @Bean
    public DirectExchange fanoutExchangeVendaFeedbck() {
        log.info("Gerando exchange: " + VENDA_FEEDBACK_EXCHANGE);
        return new DirectExchange(VENDA_FEEDBACK_EXCHANGE);
    }

    @Bean
    public Queue queueFeedbackVenda() {
        log.info("Gerando fila: " + VENDA_FEEDBACK_QUEUE);
        return new Queue(VENDA_FEEDBACK_QUEUE);
    }

    @Bean
    public Binding bindingVendaFeedbackError() {
        return BindingBuilder
                .bind(new Queue(VENDA_FEEDBACK_ERRO_QUEUE))
                .to(new DirectExchange(VENDA_FEEDBACK_EXCHANGE)).with(VENDA_FEEDBACK_ERRO_ROUTING_KEY);
    }

    @Bean
    public Binding bindingVendaFeedbackSucess() {
        return BindingBuilder
                .bind(new Queue(VENDA_FEEDBACK_SUCESSO_QUEUE))
                .to(new DirectExchange(VENDA_FEEDBACK_EXCHANGE)).with(VENDA_FEEDBACK_SUCESSO_ROUTING_KEY);
    }
}
