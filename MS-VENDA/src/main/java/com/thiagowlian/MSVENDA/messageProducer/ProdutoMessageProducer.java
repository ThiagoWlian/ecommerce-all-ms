package com.thiagowlian.MSVENDA.messageProducer;

import com.thiagowlian.MSVENDA.dto.ReducaoEstoqueDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.thiagowlian.MSVENDA.messageProducer.FilasMensageria.*;

@Slf4j
@Component
public class ProdutoMessageProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void producerReducaoEstoque(ReducaoEstoqueDto idsReducaoEstoque) {
        log.info("Enviando redução estoque!");
        rabbitTemplate.convertAndSend(VENDAS_REALIZAR_VENDA_CHOREOGRAPHY_EXCHANGE, PRODUTO_DIMINUIR_ESTOQUE_ROUTING_KEY, idsReducaoEstoque);
    }
}

