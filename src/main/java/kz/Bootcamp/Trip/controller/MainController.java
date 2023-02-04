package kz.Bootcamp.Trip.controller;

import kz.Bootcamp.Trip.model.*;
import kz.Bootcamp.Trip.service.*;
import lombok.RequiredArgsConstructor;
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
    private final PermissionService permissionService;
    private final FileUploadService fileUploadService;
    private final TourService tourService;
    private final PlaceService placeService;
    private final ItemService itemService;
    private final RequestService requestService;

    @GetMapping(value = {"/","home"})
    public String home(){
        return "home";
    }

    @GetMapping(value ="/tour")
    public String tour_list(Model model){
//        model.addAttribute("tours", tourService.getAllTours());
        return "tour-list";
    }

    @GetMapping(value ="/excursion")
    public String excursion_list(Model model){
//        model.addAttribute("tours", tourService.getAllTours());
        return "excursion-list";
    }

    @GetMapping(value ="contacts")
    public String contacts(){
        return "contacts";
    }

    @GetMapping(value ="/tour/{id}")
    public String tour(Model model, @PathVariable(name = "id") Long id){
//        model.addAttribute("tour", tourService.getTour(id));
//        model.addAttribute("desc", tourService.getTourDesc(id));
        model.addAttribute("id", id);
        return "tour";
    }

    @GetMapping(value ="/excursion/{id}")
    public String excursion(Model model, @PathVariable(name = "id") Long id){
        model.addAttribute("tour", tourService.getTour(id));
//        model.addAttribute("desc", tourService.getTourDesc(id));
        model.addAttribute("id", id);
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

//    @GetMapping(value="/admin")
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
//    public String admin(Model model){
//        return "admin";
//    }

    @GetMapping(value="/admin")
    public String admin(Model model){
        return "admin-panel/admin";
    }

    @GetMapping(value="/admin-users")
    public String adminUsers(Model model){
        model.addAttribute("permissions", permissionService.getAllPermissions());
        model.addAttribute("users", userService.getAllUsers());
        return "admin-panel/users";
    }

    @PostMapping(value = "/addUser")
    public String addUser(@RequestParam(name = "user_full_name") String userFullName,
                           @RequestParam(name = "user_email") String userEmail,
                           @RequestParam(name = "user_password") String userPassword,
                           @RequestParam(name = "user_re_password") String userRePassword){
        userService.registerUser(userFullName,userEmail,userPassword,userRePassword);
        return "redirect:/admin-users";
    }
    @PostMapping(value = "/editUser")
    public String editUser(@RequestParam(name = "permissions", required = false) List<Permission> permissions,
                                 User user){
        userService.updateUser(permissions, user);
        return "redirect:/admin-users";
    }

    @PostMapping(value = "/deleteUser")
    public String deleteUser(@RequestParam(name="user_id") Long id){
        userService.deleteUser(id);
        return "redirect:/admin-users";
    }

    @GetMapping(value="/admin-permissions")
    public String adminPermissions(Model model){
        model.addAttribute("permissions", permissionService.getAllPermissions());
//        model.addAttribute("users", userService.getAllUsers());
        return "admin-panel/permissions";
    }

    @PostMapping(value = "/addPermission")
    public String addPermission(@RequestParam(name="name") String permissionName){
        permissionService.addPermission(permissionName);
        return "redirect:/admin-permissions";
    }

    @PostMapping(value = "/editPermission")
    public String editPermission(@RequestParam(name="id") Long permissionId,
                                 @RequestParam(name="permission") String permissionName){
        permissionService.updatePermission(permissionId,permissionName);
        return "redirect:/admin-permissions";
    }

    @PostMapping(value = "/deletePermission")
    public String deletePermission(@RequestParam(name="id") Long permissionId){
        permissionService.deletePermission(permissionId);
        return "redirect:/admin-permissions";
    }

    @GetMapping(value = "/admin-trips")
    public String adminTrips(Model model){
        model.addAttribute("trips", tourService.getAllTrips());
        return "admin-panel/trips";
    }

    @GetMapping(value = "/admin-trip-details/{id}")
    public String adminTripDetails(@PathVariable(name="id") Long tripId,
            Model model){
        model.addAttribute("trip", tourService.getTrip(tripId));
        return "admin-panel/trip-details";
    }

    @GetMapping(value = "/admin-places")
    public String adminPlaces(Model model){
        model.addAttribute("places", placeService.getAllPlaces());
        return "admin-panel/places";
    }

    @PostMapping(value = "/addPlace")
    public String addPlace(Place place){
        placeService.addPlace(place);
        return "redirect:/admin-places";
    }

    @PostMapping(value = "/editPlace")
    public String editPlace(Place place){
        placeService.updatePlace(place);
        return "redirect:/admin-places";
    }

    @PostMapping(value = "/deletePlace")
    public String deletePlace(@RequestParam(name="id") Long placeId){
        placeService.deletePlace(placeId);
        return "redirect:/admin-places";
    }

    @GetMapping(value = "/admin-items")
    public String adminItems(Model model){
        model.addAttribute("items", itemService.getAllItems());
        return "admin-panel/items";
    }

    @PostMapping(value = "/addItem")
    public String addItem(Item item){
        itemService.addItem(item);
        return "redirect:/admin-items";
    }

    @PostMapping(value = "/editItem")
    public String editItem(Item item){
        itemService.updateItem(item);
        return "redirect:/admin-items";
    }

    @PostMapping(value = "/deleteItem")
    public String deleteItem(@RequestParam(name="id") Long itemId){
        itemService.deleteItem(itemId);
        return "redirect:/admin-items";
    }

    @GetMapping(value = "/admin-requests")
    public String adminRequests(Model model){
        model.addAttribute("requests", requestService.getAllRequests());
        return "admin-panel/requests";
    }

    @PostMapping(value = "/addRequest")
    public String addRequest(Request request){
        requestService.addRequest(request);
        return "redirect:/admin-requests";
    }

    @PostMapping(value = "/editRequest")
    public String editRequest(Request request){
        requestService.updateRequest(request);
        return "redirect:/admin-requests";
    }

    @PostMapping(value = "/deleteRequest")
    public String deleteRequest(@RequestParam(name="id") Long requestId){
        requestService.deleteRequest(requestId);
        return "redirect:/admin-requests";
    }

    @GetMapping(value = "/admin-orders")
    public String adminOrders(Model model){
        model.addAttribute("trips", requestService.getAllRequests());
        return "admin-panel/orders";
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
        model.addAttribute("tours", tourService.getToursSearchDto(key,order));
        return "search";
    }

//    @GetMapping(value = "/search")
//    public String search(){
////        model.addAttribute("tours", tourService.getToursSearch(key,order));
//        return "search";
//    }

//    @PostMapping(value = "/addRequest")
//    public String addRequest(Request request){
//        requestService.addRequest(request);
//        return "redirect:/contacts";
//    }
}
