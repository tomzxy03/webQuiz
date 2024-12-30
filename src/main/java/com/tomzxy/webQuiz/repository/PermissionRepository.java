package com.tomzxy.webQuiz.repository;

import com.tomzxy.webQuiz.model.Permission;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission,String > {

    @Query("SELECT p.name FROM Permission p")
    List<String> findAllPermissionIds();
    boolean existsById(String Id);

    boolean existsAllByName(String[] name);
}
