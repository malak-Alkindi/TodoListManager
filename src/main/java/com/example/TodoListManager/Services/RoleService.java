package com.example.TodoListManager.Services;



import com.example.TodoListManager.Models.Role;
import com.example.TodoListManager.Repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepo;

    public List<Role> findAll() {

        return roleRepo.findAll();
    }

    public Role findById(Long id) {

        return roleRepo.findById(id).orElse(null);
    }

    public Role findByName(String name) {

        return roleRepo.findByName(name);
    }

    public Role save(Role entity) {

        return roleRepo.save(entity);
    }
}

