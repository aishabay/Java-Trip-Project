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
    private final PriceService priceService;
    private final ItemService itemService;
    private final DescriptionService descriptionService;
    private final RequestService requestService;
    private final OrderService orderService;

    @GetMapping(value = {"/","home"})
    public String home(){
        return "home";
    }

    @GetMapping(value ="/tour")
    public String tour_list(Model model){
        return "tour-list";
    }

    @GetMapping(value ="/excursion")
    public String excursion_list(Model model){
        return "excursion-list";
    }

    @GetMapping(value ="contacts")
    public String contacts(){
        return "contacts";
    }

    @GetMapping(value ="/tour/{id}")
    public String tour(Model model, @PathVariable(name = "id") Long id){
        model.addAttribute("id", id);
        return "tour";
    }

    @GetMapping(value ="/excursion/{id}")
    public String excursion(Model model, @PathVariable(name = "id") Long id){
        model.addAttribute("tour", tourService.getTour(id));
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

    @GetMapping(value="/admin")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String admin(Model model){
        return "admin-panel/admin";
    }

    @GetMapping(value="/admin-users")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String adminUsers(Model model){
        model.addAttribute("permissions", permissionService.getAllPermissions());
        model.addAttribute("users", userService.getAllUsers());
        return "admin-panel/users";
    }

    @PostMapping(value = "/addUser")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String addUser(@RequestParam(name = "user_full_name") String userFullName,
                           @RequestParam(name = "user_email") String userEmail,
                           @RequestParam(name = "user_password") String userPassword,
                           @RequestParam(name = "user_re_password") String userRePassword){
        userService.registerUser(userFullName,userEmail,userPassword,userRePassword);
        return "redirect:/admin-users";
    }
    @PostMapping(value = "/editUser")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String editUser(@RequestParam(name = "permissions", required = false) List<Permission> permissions,
                                 User user){
        userService.updateUser(permissions, user);
        return "redirect:/admin-users";
    }

    @PostMapping(value = "/deleteUser")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String deleteUser(@RequestParam(name="user_id") Long id){
        userService.deleteUser(id);
        return "redirect:/admin-users";
    }

    @GetMapping(value="/admin-permissions")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String adminPermissions(Model model){
        model.addAttribute("permissions", permissionService.getAllPermissions());
        return "admin-panel/permissions";
    }

    @PostMapping(value = "/addPermission")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String addPermission(@RequestParam(name="name") String permissionName){
        permissionService.addPermission(permissionName);
        return "redirect:/admin-permissions";
    }

    @PostMapping(value = "/editPermission")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String editPermission(@RequestParam(name="id") Long permissionId,
                                 @RequestParam(name="permission") String permissionName){
        permissionService.updatePermission(permissionId,permissionName);
        return "redirect:/admin-permissions";
    }

    @PostMapping(value = "/deletePermission")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String deletePermission(@RequestParam(name="id") Long permissionId){
        permissionService.deletePermission(permissionId);
        return "redirect:/admin-permissions";
    }

    @GetMapping(value = "/admin-trips")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String adminTrips(Model model){
        model.addAttribute("trips", tourService.getAllTrips());
        return "admin-panel/trips";
    }

    @GetMapping(value = "/admin-trips/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String adminTripDetails(@PathVariable(name="id") Long tripId, Model model){
        model.addAttribute("trip", tourService.getTrip(tripId));
        model.addAttribute("places", placeService.getAllPlaces());
        model.addAttribute("placesShort", placeService.getAllPlacesShortByTourId(tripId));
        model.addAttribute("placesLong", placeService.getAllPlacesLongByTourId(tripId));
        model.addAttribute("prices", priceService.getAllPricesByTourId(tripId));
        model.addAttribute("items", itemService.getAllItems());
        model.addAttribute("descriptions", descriptionService.getAllDescByTourId(tripId));
        return "admin-panel/trip-details";
    }

    @PostMapping(value = "addTrip")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String addTrip(Tour tour){
        tourService.addTour(tour);
        return "redirect:/admin-trips";
    }

//    @PostMapping(value = "/assignPlaceShort")
//    public String assignPlaceShort(@RequestParam(name="trip_id") Long tripId,
//                                   @RequestParam(name="place_short_id") Long placeId){
//        placeService.assignPlaceShort(tripId, placeId);
//        return "redirect:/admin-trips/"+tripId;
//    }

    @PostMapping(value = "/assignPlacesShort")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String assignPlaceShort(@RequestParam(name="trip_id") Long tripId,
                                   @RequestParam(name="places") List<Place> places){
        placeService.assignPlacesShort(tripId, places);
        return "redirect:/admin-trips/"+tripId;
    }

    @PostMapping(value = "/unassignPlaceShort")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String unassignPlaceShort(@RequestParam(name = "trip_id") Long tripId,
                                     @RequestParam(name="place_short_id") Long placeId){
        placeService.unassignPlaceShort(tripId, placeId);
        return "redirect:/admin-trips/"+tripId;
    }

//    @PostMapping(value = "/assignPlaceLong")
//    public String assignPlaceLong(@RequestParam(name = "trip_id") Long tripId,
//                                  @RequestParam(name="place_long_id") Long placeId){
//        placeService.assignPlaceLong(tripId, placeId);
//        return "redirect:/admin-trips/"+tripId;
//    }

    @PostMapping(value = "/assignPlacesLong")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String assignPlaceLong(@RequestParam(name = "trip_id") Long tripId,
                                  @RequestParam(name="places") List<Place> places){
        placeService.assignPlacesLong(tripId, places);
        return "redirect:/admin-trips/"+tripId;
    }

    @PostMapping(value = "/unassignPlaceLong")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String unassignPlaceLong(@RequestParam(name = "trip_id") Long tripId,
                                    @RequestParam(name="place_long_id") Long placeId){
        placeService.unassignPlaceLong(tripId, placeId);
        return "redirect:/admin-trips/"+tripId;
    }

    @PostMapping(value = "/addPrice")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String unassignPlaceLong(@RequestParam(name = "trip_id") Long tripId,
                                    Price price,
                                    @RequestParam(name = "items") List<Item> items){
        priceService.addPrice(tripId, price);
        itemService.assignItems(priceService.getPriceByTripIdAndNameAndPrice(tripId, price.getName(), price.getPrice()),items);
        return "redirect:/admin-trips/"+tripId;
    }

    @PostMapping(value = "/deletePrice")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String deletePrice(@RequestParam(name = "trip_id") Long tripId,
                              @RequestParam(name = "price_id") Long priceId){
        priceService.deletePrice(priceId);
        return "redirect:/admin-trips/"+tripId;
    }

    @PostMapping(value = "/addDescription")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String assignDescriptions(@RequestParam(name = "trip_id") Long tripId,
                                  @RequestParam(name="description_name") String descriptionName){
        descriptionService.assignDescription(tripId, descriptionName);
        return "redirect:/admin-trips/"+tripId;
    }

    @PostMapping(value = "/deleteDescription")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String deleteDescription(@RequestParam(name = "trip_id") Long tripId,
                              @RequestParam(name = "description_id") Long descriptionId){
        descriptionService.deleteDescription(descriptionId);
        return "redirect:/admin-trips/"+tripId;
    }

    @PostMapping(value = "/updateTrip")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String updateTrip(@RequestParam(name = "id") Long id,
                             @RequestParam(name = "typeDuration") String typeDuration,
                             @RequestParam(name = "typeNumberPeople") String typeNumberPeople,
                             @RequestParam(name = "name") String name,
                             @RequestParam(name = "nickname") String nickname,
                             @RequestParam(name = "picture") String picture,
                             @RequestParam(name = "duration") int duration,
                             @RequestParam(name = "transfer") String transfer){
        tourService.editTour(id, typeDuration, typeNumberPeople, name, nickname, picture, duration, transfer);
        return "redirect:/admin-trips/"+id;
    }

    @PostMapping(value = "/deleteTrip")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String deleteTrip(@RequestParam(name = "id") Long id){
        tourService.deleteTour(id);
        return "redirect:/admin-trips";
    }

    @GetMapping(value = "/admin-places")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String adminPlaces(Model model){
        model.addAttribute("places", placeService.getAllPlaces());
        return "admin-panel/places";
    }

    @PostMapping(value = "/addPlace")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String addPlace(Place place){
        placeService.addPlace(place);
        return "redirect:/admin-places";
    }

    @PostMapping(value = "/editPlace")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String editPlace(Place place){
        placeService.updatePlace(place);
        return "redirect:/admin-places";
    }

    @PostMapping(value = "/deletePlace")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String deletePlace(@RequestParam(name="id") Long placeId){
        placeService.deletePlace(placeId);
        return "redirect:/admin-places";
    }

    @GetMapping(value = "/admin-items")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String adminItems(Model model){
        model.addAttribute("items", itemService.getAllItems());
        return "admin-panel/items";
    }

    @PostMapping(value = "/addItem")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String addItem(Item item){
        itemService.addItem(item);
        return "redirect:/admin-items";
    }

    @PostMapping(value = "/editItem")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String editItem(Item item){
        itemService.updateItem(item);
        return "redirect:/admin-items";
    }

    @PostMapping(value = "/deleteItem")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String deleteItem(@RequestParam(name="id") Long itemId){
        itemService.deleteItem(itemId);
        return "redirect:/admin-items";
    }

    @GetMapping(value = "/admin-requests")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String adminRequests(Model model){
        model.addAttribute("requests", requestService.getAllRequests());
        return "admin-panel/requests";
    }

    @PostMapping(value = "/addRequest")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String addRequest(Request request){
        requestService.addRequest(request);
        return "redirect:/admin-requests";
    }

    @PostMapping(value = "/editRequest")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String editRequest(Request request){
        requestService.updateRequest(request);
        return "redirect:/admin-requests";
    }

    @PostMapping(value = "/deleteRequest")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String deleteRequest(@RequestParam(name="id") Long requestId){
        requestService.deleteRequest(requestId);
        return "redirect:/admin-requests";
    }

    @GetMapping(value = "/admin-orders")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String adminOrders(Model model){
        model.addAttribute("orders", orderService.getAllOrders());
        model.addAttribute("tours", tourService.getAllTrips());
        return "admin-panel/orders";
    }

    @PostMapping(value = "/addOrder")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String addOrder(Order order){
        orderService.addOrder(order);
        return "redirect:/admin-orders";
    }

    @PostMapping(value = "/editOrder")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String editRequest(Order order){
        orderService.updateOrder(order);
        return "redirect:/admin-orders";
    }

    @PostMapping(value = "/deleteOrder")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String deleteOrder(@RequestParam(name="id") Long orderId){
        orderService.deleteOrder(orderId);
        return "redirect:/admin-orders";
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
}
