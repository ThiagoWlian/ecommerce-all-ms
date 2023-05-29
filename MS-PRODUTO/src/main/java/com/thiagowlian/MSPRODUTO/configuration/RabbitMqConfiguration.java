package com.thiagowlian.MSPRODUTO.configuration;

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

import static com.thiagowlian.MSPRODUTO.messageBroker.FilasMensageria.*;

@Slf4j
@Configuration
public class RabbitMqConfiguration {

    @Bean
    public Queue queueNovoProdutoQueryTable() {
        return new Queue(PRODUTO_NOVO_QUERY_TABLE_QUEUE);
    }

    @Bean
    public Queue queueUpdateProdutoQueryTable() {
        return new Queue(PRODUTO_UPDATE_QUERY_TABLE_QUEUE);
    }

    @Bean
    public FanoutExchange fanoutExchangeNovoProdutoQueryTable() {
        return new FanoutExchange(PRODUDO_NOVO_EXCHANGE);
    }

    @Bean
    public FanoutExchange fanoutExchangeUpdateProdutoQueryTable() {
        return new FanoutExchange(PRODUDO_UPDATE_EXCHANGE);
    }

    @Bean
    public Binding bindingProdutoUpdated() {
        return BindingBuilder
                .bind(new Queue(PRODUTO_UPDATE_QUERY_TABLE_QUEUE))
                .to(new FanoutExchange(PRODUDO_UPDATE_EXCHANGE));
    }

    @Bean
    public Binding bindingProdutoNovo() {
        return BindingBuilder
                .bind(new Queue(PRODUTO_NOVO_QUERY_TABLE_QUEUE))
                .to(new FanoutExchange(PRODUDO_NOVO_EXCHANGE));
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
