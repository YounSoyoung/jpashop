package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")  //테이블 이름을 Order가 아닌 orders로 지정
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne //order와 member는 다대일 관계 -> member를 연관관계 주인으로 잡는다
    @JoinColumn(name = "member_id") //매핑을 무엇으로 할건지 지정 -> foreign key는 member_id
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;  //주문시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status;  //주문상태 [ORDER, CANCEL]
}
