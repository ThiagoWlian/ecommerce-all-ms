package com.thiagowlian.MSVENDA.dto;

import java.util.List;

public record VendaFeedbackErrorDto(Long vendaId, List<String> produtos) {
}
