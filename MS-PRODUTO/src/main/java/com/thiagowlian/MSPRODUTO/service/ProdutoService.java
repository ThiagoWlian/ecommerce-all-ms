package com.thiagowlian.MSPRODUTO.service;

import com.thiagowlian.MSPRODUTO.dto.modificacaoEstoqueDto;
import com.thiagowlian.MSPRODUTO.messageBroker.producer.ProdutoProducer;
import com.thiagowlian.MSPRODUTO.model.ProdutoModel;
import com.thiagowlian.MSPRODUTO.model.document.EventModel;
import com.thiagowlian.MSPRODUTO.model.document.EventType;
import com.thiagowlian.MSPRODUTO.repository.EventRepository;
import com.thiagowlian.MSPRODUTO.repository.ProdutoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProdutoService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoProducer produtoProducer;

    public List<ProdutoModel> buscarTodosProdutos() {
        return produtoRepository.findAll();
    }

    public Optional<ProdutoModel> buscarProdutoPorCodigoBarra(String codigosBarra) {
        return produtoRepository.findByCodigoBarra(codigosBarra);
    }

    public List<ProdutoModel> buscarProdutoPorListaCodigoBarra(List<String> codigosBarra) {
        return produtoRepository.findAllByCodigosBarra(codigosBarra);
    }

    public void reduzirEstoqueProdutoEmUm(List<ProdutoModel> produtoModels) {
        List<EventModel> eventModels = produtoModels.stream()
                .map(e -> new EventModel(EventType.REDUZIR_ESTOQUE, new modificacaoEstoqueDto(e.getCodigoBarras(), e.reduzirEstoqueEmUm())))
                .toList();
        this.atualizarProdutos(eventModels);
        produtoProducer.publishProdutosUpdateEvent(produtoModels);
    }

    public void aumentarEstoqueProdutoEmUm(List<ProdutoModel> produtoModels) {
        List<EventModel> eventModels = produtoModels.stream()
                .map(e -> new EventModel(EventType.AUMENTAR_ESTOQUE, new modificacaoEstoqueDto(e.getCodigoBarras(), e.reduzirEstoqueEmUm())))
                .toList();
        this.atualizarProdutos(eventModels);
        produtoProducer.publishProdutosUpdateEvent(produtoModels);
    }

    public void reverterReducaoEstoque(List<String> ids) {

    }

    @Transient
    public void atualizarProdutos(List<EventModel> eventModels){
        try {
            eventRepository.insert(eventModels);
            produtoProducer.publishProdutosUpdateEvent(eventModels.stream().map(e -> (ProdutoModel)e.getContent()).toList());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public void deletarproduto(ProdutoModel produto) {
        eventRepository.insert(new EventModel(EventType.REMOVER_PRODUTO, produto));
    }

    public void cadastrarProduto(ProdutoModel produtoModel) {
        try {
            produtoRepository.insert(produtoModel);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

    public void atualizarProduto(ProdutoModel produtoModel) {
        try {
            produtoRepository.update(produtoModel);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

    public boolean reducaoIsValid(List<String> codigosBarraReducao, List<ProdutoModel> produtoFind) {
        return codigosBarraReducao.size() == produtoFind.size() && !produtoFind.isEmpty();
    }
}
