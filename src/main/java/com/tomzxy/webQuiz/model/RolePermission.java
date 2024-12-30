package com.tomzxy.webQuiz.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "role_permission")

public class RolePermission extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "role_id")
    Role role;


    @ManyToOne
    @JoinColumn(name = "permission_name")
    Permission permission;

    @Column(name= "objectType")
    String objectType;

    public RolePermission(String objectType, Permission permission){
        this.objectType= objectType;
        this.permission=permission;
    }
}
