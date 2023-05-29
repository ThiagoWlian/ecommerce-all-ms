package com.thiagowlian.MSVENDA.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record ProdutoForm(@PositiveOrZero @NotNull String codigoBarra, @PositiveOrZero double valor) {
}
