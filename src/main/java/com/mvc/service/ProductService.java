package com.mvc.service;

import java.util.List;
import java.util.stream.Collectors;

import com.mvc.dto.ProductDto;
import com.mvc.exception.ResourceNotFoundException;
import com.mvc.repo.ProductRepo;
import com.mvc.service.mapper.ProductMapper;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductMapper productMapper;
    private final ProductRepo productRepo;

    public List<ProductDto> getProducts() {
        return productRepo.findAll(Sort.by(Sort.Direction.DESC, "id"))

                .stream()
                .map(p -> productMapper.toProductDto(p))
                .collect(Collectors.toList());
    }

    public ProductDto getProduct(Integer id) {
        // return Optional.ofNullable(productRepo.findById(id).get())
        // tra ve mot optionnal chua doi tuong la tham so ben trong
        // neu khong se tra ve 1 optional Rong

        return productRepo.findById(id)
                .map(p -> productMapper.toProductDto(p))
                // truong hop Optional khac rong ham se tra ve mot ProductDto
                // .orElse(null);
                .orElseGet(() -> {
                    log.warn("--WARN: Product not found--");
                    throw new ResourceNotFoundException("Product not found");

                });
        // neu optional Rong thi tra ve doi tuong la tham so cua ham(tuc la null)
        // binh thuong neu tra ve null thi se handle ben controller return 1 error cho
        // view de hien thi ra
    }

    // public ProductDto getProduct(Integer id) {
    // return Optional.ofNullable(ProductRepo.getProduct(id))
    // //tra ve mot optionnal chua doi tuong la tham so ben trong
    // //neu khong se tra ve 1 optional Rong
    //
    //
    // .map(p -> productMapper.toProductDto(p))
    // //truong hop Optional khac rong ham se tra ve mot ProductDto
    // .orElseGet(()->{
    // log.warn("---WARNNING: Product not found---");
    // return null;
    // });
    // //neu optional Rong thi tra ve doi tuong la tham so cua ham(tuc la null)
    //
    // }
}
