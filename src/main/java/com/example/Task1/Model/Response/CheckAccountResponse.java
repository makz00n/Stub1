package com.example.Task1.Model.Response;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CheckAccountResponse {
    private String account;
    @JsonProperty("vip-client")
    private boolean vipClient;
    private boolean blocked;
    private String inn;
    private List<SpecificModelDebt> debt;
}
