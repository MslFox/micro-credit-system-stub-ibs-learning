package com.mslfox.stub.service;

import com.mslfox.stub.model.response.CheckAccountResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

@Slf4j
@Service
public class AccountService {
    public CheckAccountResponse buildCheckAccountResponse(String acc, int days) {
        var debts = IntStream.range(0, days)
                .mapToObj(i -> {
                    var isEven = i % 2 == 0;
                    return new CheckAccountResponse.Debt(
                            isEven ? 1000 : 3000,
                            isEven ? "parking" : "gkh");
                })
                .toList();
        var vip = Character.getNumericValue(acc.charAt(acc.length() - 1)) % 2 != 0;
        var response = new CheckAccountResponse(acc, vip, false, acc + "111", debts);
        log.info("<<< [CheckAccountService] response: {}", response);
        return response;
    }
}
