package com.mvc.dto.request;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateOrderRequest {
    @NotNull(message = "product id is required")
    private Integer productId;
    @NotNull(message = "user id is required")
    private String userId;
}

