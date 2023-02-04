package kz.Bootcamp.Trip.service.impl;

import kz.Bootcamp.Trip.model.Permission;
import kz.Bootcamp.Trip.model.User;
import kz.Bootcamp.Trip.repository.UserRepository;
import kz.Bootcamp.Trip.service.PermissionService;
import kz.Bootcamp.Trip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PermissionService permissionService;

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByEmail(username);
        if(user==null) throw new UsernameNotFoundException("User Not Found");
        return user;
    }

    @Override
    public User updateUserPassword(String oldPassword, String newPassword, String repeatNewPassword) {
        User currentUser = getCurrentUser();
        if(currentUser!=null && passwordEncoder.matches(oldPassword, currentUser.getPassword())){
            if(newPassword.equals(repeatNewPassword)){
                currentUser.setPassword(passwordEncoder.encode(newPassword));
                return userRepository.save(currentUser);
            }
        }
        return null;
    }

    @Override
    public User getCurrentUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            User user = (User) authentication.getPrincipal();
            return user;
        }
        return null;
    }

    @Override
    public User registerUser(String fullName, String email, String password, String rePassword) {
        if(password.trim().equals(rePassword.trim())){
            User user = getUserByEmail(email);
            if(user==null){
                User newUser = new User();
                newUser.setEmail(email);
                newUser.setPassword(passwordEncoder.encode(password));
                newUser.setFullName(fullName);
                newUser.setPermissions(permissionService.simpleUserPermissions());
                return userRepository.save(newUser);
            }
        }
        return null;
    }

    @Override
    public User setUserAvatar(String avatarUrl) {
        User user = getCurrentUser();
        user.setAvatar(avatarUrl);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(List<Permission> permissions, User user) {
        if(permissions!=null){
            user.setPermissions(permissions);
        }else{
            user.setPermissions(permissionService.simpleUserPermissions());
        }
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
