package com.example.Task1.Controller;

import com.example.Task1.Model.Request.PaymentRequest;
import com.example.Task1.Model.Response.CheckAccountResponse;
import com.example.Task1.Model.Response.PaymentResponse;
import com.example.Task1.Service.FinanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@Slf4j
public class FinanceStoreController {
    @Autowired
    private FinanceService financeService;

    @GetMapping("/api/v2/checkAccount")
    public ResponseEntity<CheckAccountResponse> checkAccount(
            @RequestParam("acc") String acc,
            @RequestParam("days") int days) {
        log.info("checkAccount");
        CheckAccountResponse response = financeService.buildCheckAccountResponse(acc, days);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @PostMapping("/api/v2/payment")
    public ResponseEntity<PaymentResponse> processPayment(
            @RequestHeader("BankCode") String bankCode,
            @RequestBody PaymentRequest request) {
        log.info("payment");
        PaymentResponse response = financeService.buildPaymentResponse(bankCode, request);

        String processingTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
        return ResponseEntity.status(HttpStatus.OK)
                .header("ProcessingTIme", processingTime)
                .body(response);
    }

    @DeleteMapping("/api/v1/transactions/cleare/{id}")
    public ResponseEntity<String> clearTransaction(@PathVariable("id") String id) {
        log.info("transactions/cleare");
        financeService.simulateDelay();
        return ResponseEntity.status(100).body("deleted success");
    }

}
