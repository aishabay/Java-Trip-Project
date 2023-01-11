package kz.Bootcamp.Trip.controller;

import kz.Bootcamp.Trip.model.Tour;
import kz.Bootcamp.Trip.service.FileUploadService;
import kz.Bootcamp.Trip.service.TourService;
import kz.Bootcamp.Trip.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final UserService userService;
    private final FileUploadService fileUploadService;
    private final TourService tourService;

    @GetMapping(value = {"/","home"})
    public String home(){
        return "home";
    }

    @GetMapping(value ="/tour-list")
    public String tour_list(Model model){
        model.addAttribute("tours", tourService.getAllTours());
        return "tour-list";
    }

    @GetMapping(value ="contacts")
    public String contacts(){
        return "contacts";
    }

    @GetMapping(value ="/tour/{id}")
    public String tour(Model model, @PathVariable(name = "id") Long id){
        model.addAttribute("tour", tourService.getTour(id));
        model.addAttribute("desc", tourService.getTourDesc(id));
        return "tour";
    }

    @GetMapping(value="/signin")
    public String signIn(Model model){
        return "signin";
    }

    @GetMapping(value="/profile")
    @PreAuthorize("isAuthenticated()")
    public String profile(Model model){
        return "profile";
    }

    @GetMapping(value="/admin")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String admin(Model model){
        return "admin";
    }

    @PostMapping(value = "/updatepassword")
    @PreAuthorize("isAuthenticated()")
    public String updatePassword(@RequestParam(name = "old_password") String oldPassword,
                                 @RequestParam(name = "new_password") String newPassword,
                                 @RequestParam(name = "repeat_new_password") String repeatNewPassword){
        userService.updateUserPassword(oldPassword, newPassword, repeatNewPassword);
        return "redirect:/profile";
    }

    @GetMapping(value="/signup")
    public String signUp(Model model){
        return "signup";
    }

    @PostMapping(value = "/register")
    public String register(@RequestParam(name = "user_full_name") String userFullName,
                           @RequestParam(name = "user_email") String userEmail,
                           @RequestParam(name = "user_password") String userPassword,
                           @RequestParam(name = "user_re_password") String userRePassword){
        if(userService.registerUser(userFullName,userEmail,userPassword,userRePassword)!=null){
            return "redirect:/signup?success";
        }else{
            return "redirect:/signup?error";
        }
    }

    @PostMapping(value="/uploadpic")
    @PreAuthorize("isAuthenticated()")
    public String uploadAvatar(@RequestParam(name = "pic") MultipartFile file){
        fileUploadService.uploadAva(file);
        return "redirect:/profile";
    }

    @GetMapping(value = "/viewpic/{token}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    @PreAuthorize("isAuthenticated()")
    public @ResponseBody byte[] viewPic(@PathVariable(name = "token") String token) throws IOException {
        return fileUploadService.getAvatar(token);
    }

    @GetMapping(value = "/search")
    public String search(@RequestParam(name = "key", required = false, defaultValue = "") String key,
                         @RequestParam(name = "order", required = false, defaultValue = "asc") String order,
                         Model model){
        model.addAttribute("tours", tourService.getToursSearch(key,order));

        return "search";
    }
}
