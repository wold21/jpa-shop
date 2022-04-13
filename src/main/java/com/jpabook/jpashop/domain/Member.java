package com.jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Address;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    private Long id;

    private String username;

//    @Embedded
//    private Address address;
//
//    @OneToMany(mappedBy = "member")
//    private List<Order> orders = new ArrayList<>();

}
