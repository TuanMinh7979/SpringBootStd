package com.mvc.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseDto {

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

}
