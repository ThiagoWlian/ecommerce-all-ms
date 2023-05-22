package com.thiagowlian.MSPRODUTO.dto;

import java.util.List;

public record ReducaoEstoqueTransactionEventDto(long vendaId, List<ProdutoNfDto> produtoDtos) {
}
