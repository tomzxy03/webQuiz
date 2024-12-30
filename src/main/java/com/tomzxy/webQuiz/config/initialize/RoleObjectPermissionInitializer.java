package com.tomzxy.webQuiz.config.initialize;

import com.tomzxy.webQuiz.constants.PredefinedRole;
import com.tomzxy.webQuiz.exception.ResourceNotFoundException;
import com.tomzxy.webQuiz.model.*;
import com.tomzxy.webQuiz.repository.PermissionRepository;
import com.tomzxy.webQuiz.repository.RolePermissionRepository;
import com.tomzxy.webQuiz.repository.RoleRepository;
import com.tomzxy.webQuiz.repository.UserRepository;
import com.tomzxy.webQuiz.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@Order(2)
public class RoleObjectPermissionInitializer {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final RolePermissionRepository rolePermissionRepository;
    private final RoleService roleService;
    @NonFinal
    static final String ADMIN_USER_NAME = "admin";

    @NonFinal
    static final String ADMIN_PASSWORD = "1111";

    @NonFinal
    static final String BASIC_USER_NAME = "tomzxy03";

    @NonFinal
    static final String BASIC_PASSWORD = "1111";

    @NonFinal
    static final String[] objectModelAdmin = {
            "subject",
            "chapter",
            "user",
            "notification",
            "group",
            "quiz",
            "question",
            "answer",
            "quiz_result",
            "answer_user",
            "role",
            "permission"
    };
    @NonFinal
    static final Map<String, List<String>> objectModelUser = Map.of(
            "subject", List.of("VIEW"),
            "chapter", List.of("VIEW"),
            "user", List.of("VIEW", "UPDATE"),
            "notifications", List.of("VIEW"),
            "group", List.of("VIEW", "CREATE"), // join group ( add user to group)
            "quiz", List.of("VIEW"),
            "quiz_result", List.of("VIEW", "CREATE"),
            "question", List.of("VIEW"),
            "answer", List.of("VIEW"),
            "answer_user", List.of("VIEW")
    );
    @NonFinal
    static final Map<String, List<String>> objectModelGroup = Map.of(

            "user", List.of("CREATE"),
            "notifications", List.of("CREATE"),
            "group", List.of("CREATE"), // create group
            "quiz", List.of("CREATE"),
            "quiz_result", List.of( "DELETE"),
            "answer_user", List.of("VIEW")
    );

        @Bean
    public CommandLineRunner initDataBase() {
        return args -> {
            if(userRepository.findByUserName(ADMIN_USER_NAME).isEmpty()){

                // initialization Role Admin and Basic
                Role adminRole= new Role();
                Role basicRole = new Role();
                Role groupRole = new Role();
                if(roleRepository.findByName(PredefinedRole.ADMIN_ROLE).isEmpty()){

                    adminRole.setName(PredefinedRole.ADMIN_ROLE);
                    roleRepository.save(adminRole);
                    //initialization permission of admin
                    var role = roleRepository.findByName(adminRole.getName()).orElseThrow(()->new ResourceNotFoundException("Role not exists"));
                    List<Permission> permissions = permissionRepository.findAll();
                    for(String object: objectModelAdmin){
                        for (Permission permission: permissions){
                            RolePermission rolePermission = new RolePermission(role,permission,object);
                            rolePermissionRepository.save(rolePermission);
                        }
                    }
                }
                if(roleRepository.findByName(PredefinedRole.USER_ROLE).isEmpty()){
                    basicRole.setName(PredefinedRole.USER_ROLE);
                    roleRepository.save(basicRole);
                    //initialization permission of user
//                    var role = roleRepository.findByName(basicRole.getName()).orElseThrow(()->new ResourceNotFoundException("Role not exists"));
                    roleService.addRolePermissionObject(basicRole, objectModelGroup);

                }
                if(roleRepository.findByName(PredefinedRole.GROUP_ROLE).isEmpty()){
                    groupRole.setName(PredefinedRole.GROUP_ROLE);
                    roleRepository.save(groupRole);
                    //initialization permission of group creator
//                    var role = roleRepository.findByName(groupRole.getName()).orElseThrow(()->new ResourceNotFoundException("Role not exists"));
                    roleService.addRolePermissionObject(groupRole, objectModelGroup);

                }








                User userAdmin = User.builder()
                        .userName(ADMIN_USER_NAME)
                        .roles(new HashSet<>(Set.of(adminRole)))
                        .email("admin@gmail.com")
                        .build();

                String endCodedAdminPassword = passwordEncoder.encode(ADMIN_PASSWORD);
                userAdmin.setPassword(endCodedAdminPassword);
                userRepository.save(userAdmin);


                User userBasic = User.builder()
                        .userName(BASIC_USER_NAME)
                        .roles(new HashSet<>(Set.of(basicRole)))
                        .email("tomzxy03@gmail.com")
                        .build();
                String endCodedBasicPassword = passwordEncoder.encode(BASIC_PASSWORD);
                userBasic.setPassword(endCodedBasicPassword);
                userRepository.save(userBasic);

                System.out.println("Roles and user have been initialized.");

            }
        };
    }

}
