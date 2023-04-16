package com.thiagowlian.MSVENDA.dto;

import jakarta.validation.constraints.PositiveOrZero;

public record ProdutoForm(@PositiveOrZero long id, @PositiveOrZero double valor) {
}
