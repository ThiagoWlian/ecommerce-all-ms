package com.thiagowlian.MSPRODUTO.model;

import com.thiagowlian.MSPRODUTO.dto.ProdutoForm;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class ProdutoModel extends BaseModel{

    private String codigoBarras;
    private String nome;
    private ProdutoTipo produtoTipo;
    private double valor;
    private long estoque;
    private long reducaoEstoqueValor;

    public ProdutoModel (ProdutoForm produtoForm) {
        this.nome = produtoForm.nome();
        this.produtoTipo = ProdutoTipo.valueOf(produtoForm.tipo());
        this.valor = produtoForm.valor();
        this.codigoBarras = produtoForm.codigoBarra();
        this.estoque = produtoForm.estoque();
    }

    public ProdutoModel (String codigoBarras, String nome, String produtoTipo, Double valor, Long estoque) {
        this.codigoBarras = codigoBarras;
        this.nome = nome;
        this.produtoTipo = ProdutoTipo.valueOf(produtoTipo);
        this.valor = valor;
        this.estoque = estoque;
    }

    public void reduzirEstoque(long quantidadeReducao) {
        estoque -= quantidadeReducao;
    }

    public long reduzirEstoqueEmUm() {
        estoque -= 1;
        return estoque;
    }

    public long aumentarEstoqueEmUm() {
        estoque += 1;
        return estoque;
    }
}
