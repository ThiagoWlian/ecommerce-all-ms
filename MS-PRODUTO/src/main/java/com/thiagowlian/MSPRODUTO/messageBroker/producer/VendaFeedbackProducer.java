package com.thiagowlian.MSPRODUTO.messageBroker.producer;

import com.thiagowlian.MSPRODUTO.dto.VendaFeedbackDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.thiagowlian.MSPRODUTO.messageBroker.FilasMensageria.*;

@Slf4j
@Component
public class VendaFeedbackProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void producerVendaFeedback(VendaFeedbackDto vendaFeedbackDto) {
        rabbitTemplate.convertAndSend(VENDAS_REALIZAR_VENDA_CHOREOGRAPHY_EXCHANGE, VENDA_FEEDBACK_CHOREOGRAPHY_ROUTING_KEY, vendaFeedbackDto);
    }
}
