package com.thiagowlian.MSPRODUTO.model;

import com.thiagowlian.MSPRODUTO.dto.ProdutoForm;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public void reduzirEstoque(long quantidadeReducao) {
        estoque -= quantidadeReducao;
    }

    public long reduzirEstoqueEmUm() {
        estoque -= 1;
        return estoque;
    }
}
