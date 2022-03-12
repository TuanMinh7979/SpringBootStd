package com.mvc.service;

import com.mvc.dto.OrderDto;
import com.mvc.dto.ProductDto;
import com.mvc.dto.request.CreateOrderRequest;
import com.mvc.exception.BadRequesetException;
import com.mvc.exception.ResourceNotFoundException;
import com.mvc.model.Order;
import com.mvc.model.User;
import com.mvc.repo.OrderRepo;
import com.mvc.repo.UserRepo;
import com.mvc.service.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


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
        //CO TRANSACTIONAL THI CO THE LAY DUOC
        //NẾU KHÔNG CÓ THÌ SẼ LÀ FETCH LAXY EXCEPTION(ban đầu sẽ không lấy như khi gọi getRole thì có transactional sẽ truy vấn thêm
        // còn không có thì THROW LAZY EXCEPTION)
//        Set<Role> setr= user.getRoles();
//        for(Role r: setr){
//            System.out.println(r.getDescription());
//        }
        user.redBalance(productDto.getAmount());
        Order order = orderMapper.updateOrder(productDto.getAmount(), user.getId());
        //CÓ TRANSACTIONAL CÓ THỂ THAY ĐỔI ENTITY MÀ KHÔNG CẦN LƯU LẠI
        orderRepo.save(order);


    }


}
