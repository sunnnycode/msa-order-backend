package com.shortpingoo.order.domain.pay.controller;

import com.shortpingoo.order.domain.pay.service.PayService;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/pay")
public class PayApiController {

    private final PayService payService;

    @PostMapping("/verify/{impUid}")
    public IamportResponse<Payment> verifyPayment
            (@RequestHeader("X-User-Id") int userId,
             @PathVariable String impUid) throws IamportResponseException, IOException {

        return payService.verifyAndSavePayment(userId, impUid);
    }
}
