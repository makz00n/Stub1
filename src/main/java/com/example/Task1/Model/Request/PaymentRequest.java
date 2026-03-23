package com.example.Task1.Model.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PaymentRequest {
    @JsonProperty("transaction_id")
    private String transactionId;
    private double sum;
    @JsonProperty("need_processing")
    private boolean needProcessing;
}