package com.tomzxy.webQuiz.repository;

import com.tomzxy.webQuiz.model.RolePermission;
import com.tomzxy.webQuiz.model.RolePermissionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission, Long> {
    @Query("select new com.tomzxy.webQuiz.model.RolePermission( rp.objectType, rp.permission) from RolePermission rp where rp.role.id in :roleIds")
    List<RolePermission> findPermissionsByRoleIds(List<Long> roleIds);
}
