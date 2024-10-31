package com.order.common.entity; // 공통 엔티티 패키지 임포트

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;

@NoArgsConstructor // 기본 생성자 생성
@AllArgsConstructor // 모든 필드를 매개변수로 받는 생성자 생성
@Data // 자동으로 getter, setter, toString, equals, hashCode 메서드를 생성
@MappedSuperclass // 이 클래스를 상속받는 모든 엔티티가 이 클래스를 기준으로 매핑됨
@SuperBuilder // 빌더 패턴을 사용하여 객체 생성 시 상위 클래스 필드에 대한 접근 지원
public class BaseEntity {
    // 식별키
    @Id // JPA에서 식별자임을 나타내는 어노테이션
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 식별자 값을 자동으로 생성하는 전략 정의
    private int id; // 엔티티의 고유 식별자
}
