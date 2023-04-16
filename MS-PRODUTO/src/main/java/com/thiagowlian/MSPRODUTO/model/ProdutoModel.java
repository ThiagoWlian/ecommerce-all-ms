package com.thiagowlian.MSPRODUTO.model;

import com.thiagowlian.MSPRODUTO.dto.ProdutoForm;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "produto")
public class ProdutoModel{

    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name ="nome")
    private String nome;
    @Column(name ="tipo")
    @Enumerated(EnumType.STRING)
    private ProdutoTipo produtoTipo;
    @Column(name ="valor")
    private double valor;
    @Column(name = "estoque")
    private long estoque;
    @Transient
    private long reducaoEstoqueValor;

    public ProdutoModel (ProdutoForm produtoForm) {
        this.nome = produtoForm.nome();
        this.produtoTipo = ProdutoTipo.valueOf(produtoForm.tipo());
        this.valor = produtoForm.valor();
        this.estoque = produtoForm.estoque();
    }

    public void reduzirEstoque(long quantidadeReducao) {
        estoque -= quantidadeReducao;
    }

    public void reduzirEstoqueEmUm() {
        estoque -= 1;
    }
}
