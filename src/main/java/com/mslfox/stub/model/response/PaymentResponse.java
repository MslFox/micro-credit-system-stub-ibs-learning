package com.mslfox.stub.model.response;

import java.util.List;

public record PaymentResponse(
        String transaction_id,
        String bank_bik,
        String status,
        List<Contact> contact
) {
    public record Contact(String name, List<String> telecom) {
    }
}