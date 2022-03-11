package com.mvc.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@Table(name = "product")
@Entity
public class Product extends BaseEntity implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String code;

    private String name;

    private Integer amount;



    public Product(Integer id, String code, String name, Integer amount, String status) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.amount = amount;

        this.status = status;
    }

    private String status;

}
