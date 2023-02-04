package kz.Bootcamp.Trip.api;

import kz.Bootcamp.Trip.dto.OrderDto;
import kz.Bootcamp.Trip.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public List<OrderDto> getAllOrders(){
        return orderService.getAllOrdersDto();
    }

    @GetMapping(value = "{id}")
    public OrderDto getOrderById(@PathVariable(name = "id") Long id){
        return orderService.getOrderDto(id);
    }

    @PostMapping
    public OrderDto addOrder(@RequestBody OrderDto orderDto){
        return orderService.addOrderDto(orderDto);
    }

}
