package com.mvc.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.mvc.constant.OrderStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "order1")
public class Order extends BaseEntity implements Serializable {
    @Id
    private String id;

    @Column(name = "user_id")
    private String userId;

    private Integer amount;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;



}
