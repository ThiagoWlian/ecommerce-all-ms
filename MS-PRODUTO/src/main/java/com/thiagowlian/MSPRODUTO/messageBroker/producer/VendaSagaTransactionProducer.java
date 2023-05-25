package com.thiagowlian.MSPRODUTO.messageBroker.producer;

import com.thiagowlian.MSPRODUTO.dto.ReducaoEstoqueTransactionEventDto;
import com.thiagowlian.MSPRODUTO.dto.VendaFeedbackErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.thiagowlian.MSPRODUTO.messageBroker.FilasMensageria.*;

@Slf4j
@Component
public class VendaSagaTransactionProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void producerVendaFeedbackError(VendaFeedbackErrorDto vendaFeedbackErrorDto) {
        rabbitTemplate.convertAndSend(VENDAS_REALIZAR_VENDA_CHOREOGRAPHY_EXCHANGE, VENDA_FEEDBACK_ERRO_ROUTING_KEY, vendaFeedbackErrorDto);
    }

    public void producerReducaoEstoque(ReducaoEstoqueTransactionEventDto reducaoEstoqueTransactionEventDto) {
        rabbitTemplate.convertAndSend(VENDA_REALIZADA_PRODUDO_REDUCAO_ESTOQUE_EXCHANGE, VENDA_REALIZADA_PRODUDO_REDUCAO_ESTOQUE_ROUTING_KEY, reducaoEstoqueTransactionEventDto);
    }
}
