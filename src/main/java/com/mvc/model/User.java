package com.mvc.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mvc.constant.UserStatus;

import lombok.*;

@Entity
@Table(name = "user")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity implements Serializable {
    @Id
    private String id;

    private String username;

    private String password;

    private Integer balance;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    // @JsonIgnore
    //// (*) dung JsonIgnore thi mapstruct k render ra cac quan he nen xac dinh dung
    //// dto thi khong dung JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)

    @JoinTable(joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public void redBalance(Integer amount) {
        this.balance -= amount;

    }


}