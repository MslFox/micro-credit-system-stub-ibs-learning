package com.mslfox.stub.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TransactionalService {
    public String delete(String id, int delay) throws InterruptedException {
        Thread.sleep(delay * 1000L);
        var response = "deleted success";
        log.info("<<< [TransactionalService] id={} — completed after {}s response: {} ", id, delay, response);
        return response;
    }
}
