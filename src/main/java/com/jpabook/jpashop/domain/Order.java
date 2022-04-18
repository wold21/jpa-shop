package com.jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate; // 주문시간

    @Enumerated(EnumType.STRING)
    private OrderStatus state;
    /**
    * 자바의 Enum타입을 엔티티 클래스의 속성으로 사용할 수 있다.
     * 두가지 enum 타입이 존재하는데
     * 1. EnumType.ORDINAL : enum "순서" 값을 DB에 저장
     * 2. EnumType.STRING : enum "이름"을 DB에 저장
     * 예를 들면 enum이 다음과 같을 때
     * enum Fruit {
     *     Apple,
     *     Banana,
     *     Peach;
     * }
     * ORDINAL를 사용하여 Fruit.Apple을 셋팅하면 DB에는 1이 저장된다.
     * Banana라고 하면 2가 저장된다. "순서" 값이 저장됨.
     *
     * STRING은 그 반대로 String이 저장된다.
     * Fruit.Peach를 하면 DB에 Peach가 저장된다.
     *
     * 참고로 해당처럼 바로 사용하게 된다면 ordinal은 추후 enum 클래스가 변경되었을때 문제가 발생 할 수 있고
     * String 방식은 문자열 그대로 들어가기 때문에 비용이 낭비될 수 있다.
     * 이를 해결하기 위해 "Attribute converter"를 사용할 수 있다.
     * 대충 본 결과 String 값을 클라이언트 단에서 컨버팅하여 코드값을 DB에 던지는 것 같다.
     * */

    // 연관 관계 메서드

    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

//    public void addOrderItem(OrderItem orderItem) {
//        orderItem.add(orderItem);
//        orderItem.setOrder(this);
//    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }
}
