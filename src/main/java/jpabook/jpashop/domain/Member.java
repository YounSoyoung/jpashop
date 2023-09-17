package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue //시퀀스 같은 값을 사용
    @Column(name = "member_id") //컬럼명을 member_id로 지정
    private Long id;

    private String name;

    @Embedded //내장 타입을 포함했다는 어노테이션으로 매핑
    private Address address;

    @OneToMany(mappedBy = "member")   //하나의 회원이 여러 개의 상품을 주문하기 때문에   //mappedBy - Order 테이블에 있는 member 필드에 의해서 매핑된 거를 알려준다. -> 연관관계의 거울
    private List<Order> orders = new ArrayList<>();
}
