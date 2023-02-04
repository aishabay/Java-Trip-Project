package kz.Bootcamp.Trip.service.impl;

import kz.Bootcamp.Trip.model.Permission;
import kz.Bootcamp.Trip.repository.PermissionRepository;
import kz.Bootcamp.Trip.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<Permission> simpleUserPermissions() {
        Permission permission = permissionRepository.findByPermission("ROLE_USER");
        List<Permission> permissions = new ArrayList<>();
        permissions.add(permission);
        return permissions;
    }

    @Override
    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }

    @Override
    public Permission addPermission(String permissionName) {
        Permission permission = new Permission();
        permission.setPermission(permissionName);
        return permissionRepository.save(permission);
    }

    @Override
    public Permission updatePermission(Long permissionId, String permissionName) {
        Permission permission = new Permission();
        permission.setId(permissionId);
        permission.setPermission(permissionName);
        return permissionRepository.save(permission);
    }

    @Override
    public void deletePermission(Long id) {
        permissionRepository.deleteById(id);
    }
}
