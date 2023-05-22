package com.thiagowlian.MSNOTAFISCAL.dto;

import java.util.List;

public record VendaFeedbackSuccessDto(Long vendaId, List<ProdutoNfDto> produtos) {
}
