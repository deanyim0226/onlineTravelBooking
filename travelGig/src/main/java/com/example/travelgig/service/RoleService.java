package com.example.travelgig.service;


import com.example.travelgig.domain.Role;

import java.util.List;

public interface RoleService {

    public Role save(Role role);

    public Role findById(Long roleId);

    public List<Role> findAll();

    public void deleteRole(Long roleId);
}
