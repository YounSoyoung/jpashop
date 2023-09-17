package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

//JPA의 내장 타입
@Embeddable //어딘가에 내장 될 수 있다
@Getter
public class Address {
    private String city;
    private String street;
    private String zipcode;
}
