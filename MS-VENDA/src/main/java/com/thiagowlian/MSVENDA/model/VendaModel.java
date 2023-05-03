package com.thiagowlian.MSVENDA.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "venda")
public class VendaModel extends BaseModel{

    @Setter
    @ElementCollection
    @Column(name = "idProduto")
    @CollectionTable(name = "produto", joinColumns = @JoinColumn(name = "produto_id"))
    private List<Long> produtosId;

    @Setter
    @Column(name = "desconto")
    private double desconto;

    @Column(name = "valorTotal")
    private double valorTotal;

    @Column(name = "valorFinal")
    private double valorFinal;

    @Setter
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataVenda = Date.valueOf(LocalDate.now());

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusVenda statusVenda;

    public VendaModel (List<Long> produtosId, double desconto, List<Double> valoresProdutos) {
        this.produtosId = produtosId;
        this.desconto = desconto;
        calcularTotalVenda(valoresProdutos);
        calcularTotalFinalVenda();
        statusVenda = StatusVenda.PROCESSANDO;
    }

    public void calcularTotalVenda(List<Double> valoresProdutos) {
        valorTotal = valoresProdutos.stream().mapToDouble(Double::doubleValue).sum();
    }

    public void calcularTotalFinalVenda() {
       valorFinal = valorTotal - this.desconto;
       if (valorFinal < 0) {
           valorFinal = 0;
       }
    }
}
