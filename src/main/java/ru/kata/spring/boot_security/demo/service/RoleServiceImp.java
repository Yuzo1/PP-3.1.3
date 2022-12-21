package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import java.util.ArrayList;
import java.util.List;

public class RoleServiceImp implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> allRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleByName(String name) {
        Long id;
        for (Role role : roleRepository.findAll()) {
            if (role.getName() == name) {
                return role;
            }
        } return null;
     }
    @Override
    public List<Role> getRoleByName(String[] names) {
        List<Role> roleList = new ArrayList<>();
        for (String name : names) {
            roleList.add(getRoleByName(name));
        } return roleList;
     }
}
