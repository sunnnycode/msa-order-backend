package com.shortpingoo.order.domain.pay.service;

import com.shortpingoo.order.db.order.Order;
import com.shortpingoo.order.db.order.OrderRepository;
import com.shortpingoo.order.db.orderitem.OrderItem;
import com.shortpingoo.order.db.orderitem.OrderItemRepository;
import com.shortpingoo.order.db.pay.Pay;
import com.shortpingoo.order.db.pay.PayRepository;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class PayServiceImpl implements PayService {

    private final PayRepository payRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final IamportClient iamportClient;

    @Value("${iamport.api.secret.key}")
    private String apiSecretKey;

    @Value("${iamport.api.key}")
    private String apiKey;

    @Autowired
    public PayServiceImpl(PayRepository payRepository, OrderRepository orderRepository,
                          OrderItemRepository orderItemRepository, IamportClient iamportClient) {
        this.payRepository = payRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.iamportClient = iamportClient;
    }

    @Override
    public IamportResponse<Payment> verifyAndSavePayment(int userId, String impUid)
            throws IamportResponseException, IOException {
        // 결제 정보 조회
        IamportResponse<Payment> response = iamportClient.paymentByImpUid(impUid);
        Payment payment = response.getResponse();

        if (payment == null) {
            return null;
        }

        Pay pay = new Pay();
        pay.setImpUid(payment.getImpUid());
        pay.setMerchantUid(payment.getMerchantUid());
        pay.setAmount(String.valueOf(payment.getAmount()));
        pay.setStatus(payment.getStatus());
        pay.setCreatedAt(payment.getPaidAt().toString());
        pay.setUserId(userId);
        payRepository.save(pay);

//        List<Order> orders = orderRepository.findByUserId(userId);
//        for (Order order : orders) {
//            List<OrderItem> orderItems = orderItemRepository.findByOrder(order);
//            for (OrderItem orderItem : orderItems) {
//                // TODO 결제 정보 저장
//                orderItem.setOrder(order);
//                orderItemRepository.save(orderItem);
//            }
//        }

        return response;
    }
}
