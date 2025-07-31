package com.mslfox.stub.controller;

import com.mslfox.stub.model.request.PaymentRequest;
import com.mslfox.stub.model.response.PaymentResponse;
import com.mslfox.stub.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "Управление платежами")

public class PaymentController {
    private final PaymentService paymentService;

    @Operation(summary = "Подтвердить платеж",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Платёж проведен",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = PaymentResponse.class)))
            })
    @PostMapping("/v2/payment")
    public ResponseEntity<PaymentResponse> payment(
            @RequestBody PaymentRequest request,
            @Parameter(description = "Код банка", example = "22", required = true)
            @RequestHeader("BankCode") int bankCode) {
        log.info(">>> [POST /v2/payment] transaction_id={}, bankCode={}", request.transaction_id(), bankCode);
        var response = paymentService.process(request, bankCode);
        HttpHeaders headers = new HttpHeaders();
        headers.add("ProcessingTIme", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        return ResponseEntity.ok().headers(headers).body(response);
    }
}
