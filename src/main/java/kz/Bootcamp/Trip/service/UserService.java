package kz.Bootcamp.Trip.service;

import kz.Bootcamp.Trip.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User getUserByEmail(String email);
    User updateUserPassword(String oldPassword, String newPassword, String repeatNewPassword);
    User getCurrentUser();
    User registerUser(String fullName, String email, String password, String rePassword);
    User setUserAvatar(String avatarUrl);

}
