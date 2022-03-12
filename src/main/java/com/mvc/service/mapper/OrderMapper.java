package com.mvc.service.mapper;

import com.mvc.constant.OrderStatus;
import com.mvc.dto.OrderDto;
import com.mvc.model.Order;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", imports = OrderStatus.class)
public interface OrderMapper {
    OrderDto toOrderDto(Order order);

    @Mapping(target = "amount", source = "productAmount")
    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "status", expression = "java(OrderStatus.PAID)")
    Order updateOrder(Integer productAmount, String userId);


}
