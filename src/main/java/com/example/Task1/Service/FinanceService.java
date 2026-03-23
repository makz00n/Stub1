package com.example.Task1.Service;

import com.example.Task1.Model.Request.PaymentRequest;
import com.example.Task1.Model.Response.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Service
public class FinanceService {

    @Value("${stub.delay-seconds}")
    private int delaySeconds;

    public CheckAccountResponse buildCheckAccountResponse(String acc, int days) {

        CheckAccountResponse response = new CheckAccountResponse();
        response.setAccount(acc);
        response.setBlocked(false);

        char lastChar = acc.charAt(acc.length() - 1);
        boolean isVip = Character.getNumericValue(lastChar) % 2 != 0;
        response.setVipClient(isVip);

        response.setInn(acc + "111");

        List<SpecificModelDebt> debts = IntStream.range(0, days)
                .mapToObj(i -> createDebt(i))
                .collect(Collectors.toList());
        response.setDebt(debts);

        return response;
    }


    private SpecificModelDebt createDebt(int index) {
        SpecificModelDebt debt = new SpecificModelDebt();
        debt.setSum((index + 1) * 1000);
        String[] descriptions = {"parking", "gkh", "utilities", "tax", "loan"};
        debt.setDescription(descriptions[index % descriptions.length]);
        return debt;
    }

    public PaymentResponse buildPaymentResponse(String bankCode, PaymentRequest request) {
        int sumDigits = bankCode.chars()
                .filter(Character::isDigit)
                .map(Character::getNumericValue)
                .sum();

        List<String> telecom = generateTelecomList(sumDigits);

        SpecificModelConatct contact = new SpecificModelConatct();
        contact.setName("HL pay company");
        contact.setTelecom(telecom);

        PaymentResponse response = new PaymentResponse();
        response.setTransactionId(request.getTransactionId());
        response.setBankBik("1234567890");
        response.setStatus("accepted");
        response.setContact(List.of(contact));

        return response;
    }

    private List<String> generateTelecomList(int count) {
        List<String> result = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            int length = 6 + random.nextInt(5); // от 6 до 10
            result.add(uuid.substring(0, length));
        }
        return result;
    }

    public void simulateDelay() {
        try {
            log.info("simulate delay");
            Thread.sleep(delaySeconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        log.info("complete simluate");
    }

}
