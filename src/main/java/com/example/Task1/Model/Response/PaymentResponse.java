package com.example.Task1.Model.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class PaymentResponse {
    @JsonProperty("transaction_id")
    private String transactionId;
    @JsonProperty("bank_bik")
    private String bankBik;
    private String status;
    private List<SpecificModelConatct> contact;
}
