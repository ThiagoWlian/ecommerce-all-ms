package com.thiagowlian.MSPRODUTO.dto;

import jakarta.validation.constraints.PositiveOrZero;

public record ReduzirEstoqueForm(@PositiveOrZero String codigosBarra) {
}
