package com.shortpingoo.order.db.pay;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "pay")
public class Pay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "imp_uid")
    private String impUid;

    // 거래 고유 식별키
    @Column(name = "merchant_uid")
    private String merchantUid;

    @Column(name = "amount")
    private String amount;

    @Column(name = "status")
    private String status;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "user_id", nullable = false)
    private int userId;
}
