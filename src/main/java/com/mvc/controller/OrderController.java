package com.mvc.controller;

import com.mvc.dto.OrderDto;

import com.mvc.dto.request.CreateOrderRequest;
import com.mvc.model.Order;
import com.mvc.service.OrderService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("order")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("{id}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Integer id) {
        final OrderDto orderdto = orderService.getOrder(id);
        return new ResponseEntity<>(orderdto, HttpStatus.OK);
    }

    @PostMapping
    public CreateOrderRequest createOrder(@Valid @RequestBody final CreateOrderRequest orderRequest){
     orderService.createOrder(orderRequest);
      return orderRequest;
    }
}
