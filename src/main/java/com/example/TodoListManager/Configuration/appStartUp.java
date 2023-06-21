package com.example.TodoListManager.Configuration;

import com.example.TodoListManager.Models.Role;
import com.example.TodoListManager.Models.userInfo;
import com.example.TodoListManager.Services.RoleService;
import com.example.TodoListManager.Services.userInfroService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


@Component
@RequiredArgsConstructor
public class appStartUp implements CommandLineRunner {


    private final userInfroService userService;

    private final RoleService roleService;

    @Override
    public void run(String... args) throws Exception {


        if (roleService.findAll().isEmpty()) {
            roleService.save(new Role(null, "admin"));
            roleService.save(new Role(null, "user"));

        }


        if (userService.findAll().isEmpty()) {

            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(roleService.findByName("admin"));

            Set<Role> userRoles = new HashSet<>();
            userRoles.add(roleService.findByName("user"));




            userService.save(new userInfo(null, "malak alkidni", "ma", "123", adminRoles));
            userService.save(new userInfo(null, "fahima alhabsi", "fa", "123", userRoles));
            userService.save(new userInfo(null, "asala alkindi", "as", "123", userRoles));
        }

    }
}
