package kz.Bootcamp.Trip.service;

import kz.Bootcamp.Trip.model.Permission;

import java.util.List;

public interface PermissionService {

    List<Permission> simpleUserPermissions();

    List<Permission> getAllPermissions();

    Permission addPermission(String permissionName);

    Permission updatePermission(Long permissionId, String permissionName);

    void deletePermission(Long id);

}
