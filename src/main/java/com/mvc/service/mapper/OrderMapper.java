package com.mvc.service.mapper;

import com.mvc.dto.OrderDto;
import com.mvc.model.Order;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto toOrderDto(Order order);


}
