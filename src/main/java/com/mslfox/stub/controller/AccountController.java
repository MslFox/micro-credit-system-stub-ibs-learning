package com.mslfox.stub.controller;

import com.mslfox.stub.model.response.CheckAccountResponse;
import com.mslfox.stub.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "Инфо о задолженностях")
public class AccountController {
    private final AccountService accountService;

    @Operation(summary = "Проверить информацию о задолженностях по карте клиента",
            responses = {
                    @ApiResponse(responseCode = "202", description = "Информация о счёте найдена",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CheckAccountResponse.class)))
            })
    @GetMapping("/v2/checkAccount")
    public ResponseEntity<CheckAccountResponse> checkAccount(
            @Parameter(description = "Номер счёта", example = "1234567890")
            @RequestParam String acc,
            @Parameter(description = "Количество дней задолженности", example = "2")
            @RequestParam int days) {
        log.info(">>> [GET /v2/checkAccount] acc={}, days={}", acc, days);
        var response = accountService.buildCheckAccountResponse(acc, days);
        return ResponseEntity.status(202).body(response);
    }

}
