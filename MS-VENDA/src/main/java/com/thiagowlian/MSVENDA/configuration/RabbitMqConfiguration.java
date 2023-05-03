package com.thiagowlian.MSVENDA.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.thiagowlian.MSVENDA.messageBroker.FilasMensageria.*;

@Slf4j
@Configuration
public class RabbitMqConfiguration {

    @Bean
    public Queue queueFeedbackVendaChoreography() {
        log.info("Gerando fila: " + VENDA_FEEDBACK_CHOREOGRAPHY_QUEUE);
        return new Queue(VENDA_FEEDBACK_CHOREOGRAPHY_QUEUE);
    }

    @Bean
    public DirectExchange directExchangeRealizarVendaChoreography() {
        log.info("Gerando Exchange: " + VENDAS_REALIZADA_VENDA_CHOREOGRAPHY_EXCHANGE);
        return new DirectExchange(VENDAS_REALIZADA_VENDA_CHOREOGRAPHY_EXCHANGE);
    }

    @Bean
    public Binding bindingVendaFeedback() {
        return BindingBuilder
                .bind(new Queue(VENDA_FEEDBACK_CHOREOGRAPHY_QUEUE))
                .to(new DirectExchange(VENDAS_REALIZADA_VENDA_CHOREOGRAPHY_EXCHANGE))
                .with(VENDA_FEEDBACK_CHOREOGRAPHY_ROUTING_KEY);
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> applicationReadyEventApplicationListener(RabbitAdmin rabbitAdmin) {
        return event -> rabbitAdmin.initialize();
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter jackson2JsonMessageConverter) {
        log.info("Configurando serelizador");
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter);
        return rabbitTemplate;
    }
}
