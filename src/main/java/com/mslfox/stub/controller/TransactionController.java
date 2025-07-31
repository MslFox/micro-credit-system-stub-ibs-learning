package com.mslfox.stub.controller;

import com.mslfox.stub.service.TransactionalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "Управление транзакциями")
public class TransactionController {
    @Value("${stub.delay:1}")
    private int delay;
    private final TransactionalService transactionalService;

    @Operation(summary = "Удалить транзакцию процессинга",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Транзакция удалена успешно", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
            })
    @DeleteMapping("/v1/transactions/cleare/{id}")
    public ResponseEntity<String> delete(
            @Parameter(description = "Идентификатор транзакции", example = "abc123")
            @PathVariable String id) throws InterruptedException {
        log.info(">>> [DELETE /transactions/cleare] id={}, delay={}s — start", id, delay);
        var response = transactionalService.delete(id, delay);
        return ResponseEntity.ok(response);
    }
}
