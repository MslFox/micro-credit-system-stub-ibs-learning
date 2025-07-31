package com.mslfox.stub.model.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Информация о задолженности по счёту")
public record CheckAccountResponse(
        @Schema(description = "Номер счёта", example = "1234567890")
        String account,
        @Schema(description = "Признак VIP-клиента", example = "false")
        boolean vipClient,
        @Schema(description = "Заблокирован ли счёт", example = "false")
        boolean blocked,
        @Schema(description = "ИНН клиента", example = "1234567890111")
        String inn,
        @Schema(description = "Список задолженностей")
        List<Debt> debt
) {
    @Schema(description = "Информация о каждой задолженности")
    public record Debt(
            @Schema(description = "Сумма задолженности", example = "1000")
            int sum,
            @Schema(description = "Описание задолженности", example = "parking")
            String description
    ) {
    }
}