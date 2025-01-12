package com.ksj.bootstudy.service.role;

import com.ksj.bootstudy.model.Role;
import com.ksj.bootstudy.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role findByRoleNameContainsIgnoreCase(String roleName) {
        return roleRepository.findByRoleNameContainsIgnoreCase(roleName);
    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }
}
