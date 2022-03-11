package com.mvc.service;

import com.mvc.constant.OrderStatus;
import com.mvc.dto.OrderDto;
import com.mvc.dto.ProductDto;
import com.mvc.dto.UserDto;
import com.mvc.dto.request.CreateOrderRequest;
import com.mvc.exception.BadRequesetException;
import com.mvc.exception.ResourceNotFoundException;
import com.mvc.model.Order;
import com.mvc.model.Product;
import com.mvc.model.User;
import com.mvc.repo.OrderRepo;
import com.mvc.repo.UserRepo;
import com.mvc.service.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepo orderRepo;
    private final OrderMapper orderMapper;
    private final UserService userService;
    private final ProductService productService;
    private final UserRepo userRepo;

    public OrderDto getOrder(Integer id) {
        return orderRepo.findById(id)
                .map(orderMapper::toOrderDto)
                .orElseGet(() -> {
                    log.warn("---WARN: Order not found---");
                    throw new ResourceNotFoundException("Order not found!");


                });
    }

    @Transactional
    public void createOrder(CreateOrderRequest orderRequest) {
        ProductDto productDto = productService.getProduct(orderRequest.getProductId());
        User user = userRepo.findById(orderRequest.getUserId()).get();
        if (user.getBalance() < productDto.getAmount()) {
            log.warn("User {} do not enough balance", user.getUsername());
            throw new BadRequesetException("Balance not enough ");
        }
        Order order = new Order();
        order.setUserId(user.getId());
        order.setAmount(productDto.getAmount());
        order.setStatus(OrderStatus.PAID);


        user.setBalance(user.getBalance() - productDto.getAmount());
        orderRepo.save(order);


    }


}
