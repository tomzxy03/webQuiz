package com.tomzxy.webQuiz.repository;

import com.tomzxy.webQuiz.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Page<Role> findByNameContainingIgnoreCase(String search, Pageable pageable);
    Optional<Role> findByName(String name);

    @Query("SELECT r FROM Role r WHERE r.name IN :roleNames")
    List<Role> findAllByName(@Param("roleNames") List<String> roles);
}
