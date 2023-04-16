package com.thiagowlian.MSVENDA.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;
import java.util.stream.Collectors;

public record VendaForm(@NotEmpty List<ProdutoForm> produtos, @PositiveOrZero Double desconto){

    public List<Double> getListDesconto() {
        return produtos()
                .stream()
                .map(ProdutoForm::valor)
                .collect(Collectors.toList());
    }

    public List<Long> getListProdutoId() {
        return produtos()
                .stream()
                .map(ProdutoForm::id)
                .collect(Collectors.toList());
    }
}
