package com.shortpingoo.order.domain.pay.service;

import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

import java.io.IOException;

public interface PayService {

    IamportResponse<Payment> verifyAndSavePayment(int userId, String impUid) throws IamportResponseException, IOException;

}
