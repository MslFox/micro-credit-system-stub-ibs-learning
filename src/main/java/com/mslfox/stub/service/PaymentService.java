package com.mslfox.stub.service;

import com.mslfox.stub.model.request.PaymentRequest;
import com.mslfox.stub.model.response.PaymentResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@Slf4j
@Service
public class PaymentService {
    public PaymentResponse process(PaymentRequest request, int bankCode) {
        int telecomCount = String.valueOf(bankCode).chars()
                .map(Character::getNumericValue).sum();

        List<String> telecom = IntStream.range(0, telecomCount)
                .mapToObj(i -> UUID.randomUUID().toString().substring(0, 8))
                .toList();
        var response = new PaymentResponse(
                request.transaction_id(),
                "2345678997",
                "accepted",
                List.of(new PaymentResponse.Contact("HL pay company", telecom)));
        log.debug("<<< [PaymentService] response: {}", response);
        return response;
    }
}
