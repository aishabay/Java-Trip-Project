package kz.Bootcamp.Trip.service;

import kz.Bootcamp.Trip.model.Permission;
import kz.Bootcamp.Trip.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User getUserByEmail(String email);
    User getUserById(Long id);
    User updateUserPassword(String oldPassword, String newPassword, String repeatNewPassword);
    User getCurrentUser();
    User registerUser(String fullName, String email, String password, String rePassword);
    User setUserAvatar(String avatarUrl);
    List<User> getAllUsers();

    User updateUser(List<Permission> permissions, User user);
    void deleteUser(Long id);

}
