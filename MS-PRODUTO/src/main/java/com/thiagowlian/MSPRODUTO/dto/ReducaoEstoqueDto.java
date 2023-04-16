package com.thiagowlian.MSPRODUTO.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record ReducaoEstoqueDto(@Positive long vendaId, @NotEmpty List<Long> produtosId){

}
