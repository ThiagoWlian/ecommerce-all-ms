package com.thiagowlian.MSPRODUTO.messageBroker.producer;

import com.thiagowlian.MSPRODUTO.model.ProdutoModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.thiagowlian.MSPRODUTO.messageBroker.FilasMensageria.NULL_ROUNTING_KEY;
import static com.thiagowlian.MSPRODUTO.messageBroker.FilasMensageria.PRODUDO_NOVO_EXCHANGE;

@Slf4j
@Component
public class ProdutoProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void publishProdutoCreatedEvent(ProdutoModel produto) {
        rabbitTemplate.convertAndSend(PRODUDO_NOVO_EXCHANGE, NULL_ROUNTING_KEY, produto);
    }
}
