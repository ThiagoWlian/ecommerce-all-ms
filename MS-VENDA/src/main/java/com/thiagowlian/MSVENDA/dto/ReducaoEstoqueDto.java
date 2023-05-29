package com.thiagowlian.MSVENDA.dto;

import jakarta.validation.constraints.Positive;

import java.util.List;

public record ReducaoEstoqueDto(@Positive long vendaId, @Positive List<String> produtosCodigoBarra){

}
