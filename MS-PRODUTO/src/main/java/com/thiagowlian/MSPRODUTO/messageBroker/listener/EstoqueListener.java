package com.thiagowlian.MSPRODUTO.messageBroker.listener;

import com.thiagowlian.MSPRODUTO.dto.ReducaoEstoqueWithListProductsDto;
import com.thiagowlian.MSPRODUTO.dto.VendaFeedbackDto;
import com.thiagowlian.MSPRODUTO.messageBroker.producer.VendaFeedbackProducer;
import com.thiagowlian.MSPRODUTO.model.ProdutoModel;
import com.thiagowlian.MSPRODUTO.service.ProdutoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.thiagowlian.MSPRODUTO.messageBroker.FilasMensageria.VENDA_REALIZADA_PRODUTO_REDUZIR_ESTOQUE_QUEUE;

@Slf4j
@Component
public class EstoqueListener {

    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private VendaFeedbackProducer producerVendaFeedback;

    @RabbitListener(queues = VENDA_REALIZADA_PRODUTO_REDUZIR_ESTOQUE_QUEUE)
    public void onVendaCreated(ReducaoEstoqueWithListProductsDto reducaoEstoqueWithListProductsDto) {
        try {
            List<ProdutoModel> produtos = produtoService.buscarProdutoPorListaCodigoBarra(reducaoEstoqueWithListProductsDto.produtosCodigoBarra());
            if (!reducaoEstoqueWithListProductsDto.produtosCodigoBarra().isEmpty() && produtos.isEmpty()) {
                producerVendaFeedback.producerVendaFeedback(new VendaFeedbackDto(reducaoEstoqueWithListProductsDto.vendaId(), true));
            }
            produtoService.reduzirEstoqueProdutoEmUm(produtos);
        } catch (Exception ex) {
            producerVendaFeedback.producerVendaFeedback(new VendaFeedbackDto(reducaoEstoqueWithListProductsDto.vendaId(), true));
        }
    }
}
