package com.tomzxy.webQuiz.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RolePermissionId implements Serializable {
    private Long role;
    private String objectType;
    private String permission;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;
        RolePermissionId that = (RolePermissionId) o;
        return Objects.equals(role, that.role) &&
                Objects.equals(objectType, that.objectType) &&
                Objects.equals(permission, that.permission);
    }
    @Override
    public int hashCode() {
        return Objects.hash(role, objectType, permission);
    }
}
