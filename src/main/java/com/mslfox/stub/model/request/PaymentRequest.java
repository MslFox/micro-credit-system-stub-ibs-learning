package com.mslfox.stub.model.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Запрос на подтверждение платежа")
public record PaymentRequest(
        @Schema(description = "Идентификатор транзакции", example = "T-342-67777")
        String transaction_id,
        @Schema(description = "Сумма платежа", example = "133.12")
        double sum,
        @Schema(description = "Необходимость обработки", example = "true")
        boolean need_processing
) {}