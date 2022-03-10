package com.mvc.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

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
    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


}