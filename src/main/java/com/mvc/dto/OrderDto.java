package com.mvc.dto;

import com.mvc.constant.OrderStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto extends BaseDto {
    private String id;

    private String userId;

    private Integer amount;

    private OrderStatus status;

}
