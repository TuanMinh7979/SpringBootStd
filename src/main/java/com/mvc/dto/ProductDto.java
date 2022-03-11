package com.mvc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto extends BaseDto {

    private Integer id;

    private String code;

    private String name;

    private Integer amount;



    private String status;

}
