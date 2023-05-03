package com.thiagowlian.MSPRODUTO.service;

import com.thiagowlian.MSPRODUTO.model.BaseModel;
import com.thiagowlian.MSPRODUTO.model.ProdutoModel;
import com.thiagowlian.MSPRODUTO.model.document.EventModel;
import com.thiagowlian.MSPRODUTO.model.document.EventType;
import com.thiagowlian.MSPRODUTO.repository.EventRepository;
import com.thiagowlian.MSPRODUTO.repository.ProdutoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProdutoService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ProdutoModel> buscarTodosProdutos() {
        return produtoRepository.findAll();
    }

    public Optional<ProdutoModel> buscarProdutoPorId(String codigosBarra) {
        return produtoRepository.findByCodigoBarra(codigosBarra);
    }

    public List<ProdutoModel> buscarProdutoPorListaId(List<String> codigosBarra) {
        return produtoRepository.findAllByCodigosBarra(codigosBarra);
    }

    public void reduzirEstoqueProduto(List<ProdutoModel> produtoModels) {
        produtoModels.forEach(ProdutoModel::reduzirEstoqueEmUm);
        List<EventModel> eventModels = produtoModels.stream().map(e -> new EventModel(EventType.REDUZIR_ESTOQUE, e)).toList();
        eventRepository.insert(eventModels);
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

}
