package kz.Bootcamp.Trip.service;

import kz.Bootcamp.Trip.dto.OrderDto;
import kz.Bootcamp.Trip.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
    Order getOrder(Long id);
    Order addOrder(Order order);

    Order updateOrder(Order order);

    Boolean deleteOrder(Long id);

    List<OrderDto> getAllOrdersDto();
    OrderDto addOrderDto(OrderDto orderDto);
    OrderDto getOrderDto(Long id);
}
