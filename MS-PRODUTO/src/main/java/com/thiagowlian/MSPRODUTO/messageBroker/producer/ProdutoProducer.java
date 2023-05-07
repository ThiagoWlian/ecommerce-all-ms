package com.thiagowlian.MSPRODUTO.messageBroker.producer;

import com.thiagowlian.MSPRODUTO.model.ProdutoModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.thiagowlian.MSPRODUTO.messageBroker.FilasMensageria.*;

@Slf4j
@Component
public class ProdutoProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void publishProdutoCreatedEvent(ProdutoModel produto) {
        rabbitTemplate.convertAndSend(PRODUDO_NOVO_EXCHANGE, NULL_ROUNTING_KEY, produto);
    }

    public void publishProdutosUpdateEvent(List<ProdutoModel> produtos) {
        produtos.stream().forEach(e -> rabbitTemplate.convertAndSend(PRODUDO_NOVO_EXCHANGE, NULL_ROUNTING_KEY, e));
    }

    public void publishProdutosReducaoEstoqueEvent(List<ProdutoModel> produtos) {
        produtos.stream().forEach(e -> rabbitTemplate.convertAndSend(PRODUDO_UPDATE_EXCHANGE, NULL_ROUNTING_KEY, e));
    }

    public void publishProdutoReducaoEstoqueEvent(ProdutoModel produto) {
        rabbitTemplate.convertAndSend(PRODUDO_NOVO_EXCHANGE, NULL_ROUNTING_KEY, produto);
    }
}
