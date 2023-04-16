package com.thiagowlian.MSPRODUTO.service;

import com.thiagowlian.MSPRODUTO.model.ProdutoModel;
import com.thiagowlian.MSPRODUTO.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public ProdutoModel cadastrarProduto(ProdutoModel produto) {
        return produtoRepository.save(produto);
    }

    public List<ProdutoModel> buscarTodosProdutos() {
        return produtoRepository.findAll();
    }

    public Optional<ProdutoModel> buscarProdutoPorId(long id) {
        return produtoRepository.findById(id);
    }

    public List<ProdutoModel> buscarProdutoPorListaId(List<Long> ids) {
        return produtoRepository.findAllById(ids);
    }

    public void reduzirEstoqueProduto(List<ProdutoModel> produtoModels) {
        produtoModels.forEach(ProdutoModel::reduzirEstoqueEmUm);
        produtoRepository.saveAll(produtoModels);
    }

    public void deletarproduto(ProdutoModel produto) {
        produtoRepository.delete(produto);
    }

}
