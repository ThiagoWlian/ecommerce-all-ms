package com.thiagowlian.MSPRODUTO.dto;

import com.thiagowlian.MSPRODUTO.model.ProdutoModel;

public record ProdutoNfDto(String codigoBarras, String nome, double valor, long estoque) {

    public ProdutoNfDto(ProdutoModel produto) {
        this(produto.getCodigoBarras(), produto.getNome(), produto.getValor(), produto.getEstoque());
    }
}
