package com.mvc.service.mapper;

import com.mvc.dto.ProductDto;
import com.mvc.model.Product;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    // chay lan dau tien sau do dong
    // @Mapping(target="id", source="id")
    ProductDto toProductDto(final Product product);
}
