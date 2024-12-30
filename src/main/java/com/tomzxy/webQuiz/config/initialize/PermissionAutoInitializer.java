package com.tomzxy.webQuiz.config.initialize;

import com.tomzxy.webQuiz.mapper.PermissionMapper;
import com.tomzxy.webQuiz.model.Permission;
import com.tomzxy.webQuiz.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@Order(1)
public class PermissionAutoInitializer implements CommandLineRunner {
    private final PermissionRepository permissionRepository;
    private final PermissionMapper permissionMapper;
    private final Map<String,String> ACTIVES = Map.ofEntries(
            new AbstractMap.SimpleEntry<String,String>("VIEW" , "xem dữ liệu"),
            new AbstractMap.SimpleEntry<String,String>("CREATE" , "thêm dữ liệu"),
            new AbstractMap.SimpleEntry<String,String>("UPDATE" , "sửa dữ liệu"),
            new AbstractMap.SimpleEntry<String,String>("DELETE" , "xóa dữ liệu")
    );

    @Autowired
    public PermissionAutoInitializer(PermissionRepository permissionRepository, PermissionMapper permissionMapper) {
        this.permissionRepository = permissionRepository;
        this.permissionMapper = permissionMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        for(Map.Entry<String, String> item: ACTIVES.entrySet()){
            Permission permission = new Permission(item.getKey(), item.getValue());
            createPermission(permission);
        }
    }

    private void createPermission(Permission permission){
        if(!permissionRepository.existsById(permission.getName())){
            permissionRepository.save(permission);
        }
    }
}
