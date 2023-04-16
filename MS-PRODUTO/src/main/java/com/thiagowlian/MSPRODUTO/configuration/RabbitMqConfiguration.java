package com.thiagowlian.MSPRODUTO.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
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
    public Queue queueProdutoReduzirEstoque() {
        log.info("Gerando fila: " + PRODUTO_REDUZIR_ESTOQUE_QUEUE);
        return new Queue(PRODUTO_REDUZIR_ESTOQUE_QUEUE);
    }

    @Bean
    public Binding bindingProdutoReduzirEstoque() {
        return BindingBuilder
                .bind(new Queue(PRODUTO_REDUZIR_ESTOQUE_QUEUE))
                .to(new DirectExchange(VENDAS_REALIZAR_VENDA_CHOREOGRAPHY_EXCHANGE))
                .with(PRODUTO_DIMINUIR_ESTOQUE_ROUTING_KEY);
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
