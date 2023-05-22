package com.thiagowlian.MSNOTAFISCAL.dto;

import java.util.List;

public record ReducaoEstoqueTransactionEventDto(long vendaId, List<ProdutoNfDto> produtoDtos) {
}
