package com.thiagowlian.MSVENDA.messageBroker.producer;

import com.thiagowlian.MSVENDA.dto.ReducaoEstoqueDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.thiagowlian.MSVENDA.messageBroker.FilasMensageria.*;

@Slf4j
@Component
public class VendaMessageProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void producerVendaRealizada(ReducaoEstoqueDto idsReducaoEstoque) {
        rabbitTemplate.convertAndSend(VENDA_REALIZADA_EXCHANGE, VENDA_REALIZADA_ROUTING_KEY, idsReducaoEstoque);
    }
}

