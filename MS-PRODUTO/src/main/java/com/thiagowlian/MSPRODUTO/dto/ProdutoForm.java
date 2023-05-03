package com.thiagowlian.MSPRODUTO.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record ProdutoForm(@NotBlank String codigoBarra, @NotBlank String nome, @NotBlank String tipo, @NotNull @PositiveOrZero double valor, @NotNull @PositiveOrZero long estoque) {
}
