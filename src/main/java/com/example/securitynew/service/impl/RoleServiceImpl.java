package com.example.securitynew.service.impl;

import com.example.securitynew.model.Role;
import com.example.securitynew.repository.RoleRepository;
import com.example.securitynew.service.RoleService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private RoleRepository repository;

    @Override
    public Role add(Role role) {
        return repository.save(role);
    }
}
