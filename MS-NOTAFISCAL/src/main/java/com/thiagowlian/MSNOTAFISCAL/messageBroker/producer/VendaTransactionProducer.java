package com.thiagowlian.MSNOTAFISCAL.messageBroker.producer;

import com.thiagowlian.MSNOTAFISCAL.dto.VendaFeedbackErrorDto;
import com.thiagowlian.MSNOTAFISCAL.dto.VendaFeedbackSuccessDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import static com.thiagowlian.MSNOTAFISCAL.messageBroker.FilasMensageria.*;

public class VendaTransactionProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void producerVendaFeedbackError(VendaFeedbackErrorDto vendaFeedbackErrorDto) {
        rabbitTemplate.convertAndSend(VENDAS_REALIZAR_VENDA_CHOREOGRAPHY_EXCHANGE, VENDA_FEEDBACK_ERRO_ROUTING_KEY, vendaFeedbackErrorDto);
    }

    public void producerVendaFeedbackSuccess(VendaFeedbackSuccessDto vendaFeedbackErrorDto) {
        rabbitTemplate.convertAndSend(VENDAS_REALIZAR_VENDA_CHOREOGRAPHY_EXCHANGE, VENDA_FEEDBACK_SUCESSO_ROUTING_KEY, vendaFeedbackErrorDto);
    }
}
