package org.example.producer;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FraudController {

    @PutMapping(value = "/fraudcheck", consumes="application/json;charset=UTF-8", produces="application/json;charset=UTF-8")
    public String check(@RequestBody LoanRequest loanRequest) {

        if (loanRequest.getLoanAmount() > 10000) {
            return "{fraudCheckStatus: FRAUD, rejection.reason: Amount too high}";
        } else {
            return "{fraudCheckStatus: OK, acceptance.reason: Amount OK}";
        }
    }
}
