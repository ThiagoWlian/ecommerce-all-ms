package com.thiagowlian.MSPRODUTO.messageBroker.listener;

import com.thiagowlian.MSPRODUTO.model.ProdutoModel;
import com.thiagowlian.MSPRODUTO.model.document.EventModel;
import com.thiagowlian.MSPRODUTO.service.ProdutoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.thiagowlian.MSPRODUTO.messageBroker.FilasMensageria.PRODUTO_NOVO_QUERY_TABLE_QUEUE;
import static com.thiagowlian.MSPRODUTO.messageBroker.FilasMensageria.PRODUTO_UPDATE_QUERY_TABLE_QUEUE;

@Component
public class ProdutoListener {

    @Autowired
    private ProdutoService produtoService;

    @RabbitListener(queues = PRODUTO_NOVO_QUERY_TABLE_QUEUE)
    public void onCreatedProduto(ProdutoModel produto) {
        produtoService.cadastrarProduto(produto);
    }

    @RabbitListener(queues = PRODUTO_UPDATE_QUERY_TABLE_QUEUE)
    public void onUpdatedProduto(ProdutoModel produto) {
        produtoService.cadastrarProduto(produto);
    }
}
