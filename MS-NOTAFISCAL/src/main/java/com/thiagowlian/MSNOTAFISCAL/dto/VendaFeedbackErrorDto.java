package com.thiagowlian.MSNOTAFISCAL.dto;

import java.util.List;

public record VendaFeedbackErrorDto(Long vendaId, List<String> produtos) {
}
