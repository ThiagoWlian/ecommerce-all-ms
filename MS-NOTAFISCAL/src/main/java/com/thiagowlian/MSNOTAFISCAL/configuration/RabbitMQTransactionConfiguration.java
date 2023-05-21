package com.thiagowlian.MSNOTAFISCAL.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.thiagowlian.MSNOTAFISCAL.messageBroker.FilasMensageria.*;

@Slf4j
@Configuration
public class RabbitMQTransactionConfiguration {

    @Bean
    public Queue queueReducaoEstoque() {
        return new Queue(REALIZAR_VENDA_REDUCAO_ESTOQUE_TRANSACTION);
    }

    @Bean
    public Binding bindingReducaoEstoque(){
        return BindingBuilder
                .bind(new Queue(REALIZAR_VENDA_REDUCAO_ESTOQUE_TRANSACTION))
                .to(new DirectExchange(VENDA_REALIZADA_PRODUDO_REDUCAO_ESTOQUE_EXCHANGE))
                .with(VENDA_REALIZADA_PRODUDO_REDUCAO_ESTOQUE_ROUTING_KEY);
    }
}
