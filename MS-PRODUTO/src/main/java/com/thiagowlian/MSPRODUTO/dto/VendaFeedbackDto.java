package com.thiagowlian.MSPRODUTO.dto;

import java.util.List;

public record VendaFeedbackDto(Long vendaId, List<ProdutoNfDto> produtos) {
}
