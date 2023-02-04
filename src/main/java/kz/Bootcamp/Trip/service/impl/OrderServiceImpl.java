package kz.Bootcamp.Trip.service.impl;

import kz.Bootcamp.Trip.dto.OrderDto;
import kz.Bootcamp.Trip.mapper.OrderMapper;
import kz.Bootcamp.Trip.model.Order;
import kz.Bootcamp.Trip.repository.OrderRepository;
import kz.Bootcamp.Trip.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order getOrder(Long id) {
        return orderRepository.findById(id).orElseThrow();
    }

    @Override
    public Order updateOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Boolean deleteOrder(Long id) {
        Order order = getOrder(id);
        if(order!=null){
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<OrderDto> getAllOrdersDto() {
        return orderMapper.toDtoList(getAllOrders());
    }
    @Override
    public OrderDto getOrderDto(Long id) {
        return orderMapper.toDto(getOrder(id));
    }

    @Override
    public OrderDto addOrderDto(OrderDto orderDto) {
        return orderMapper.toDto(addOrder(orderMapper.toEntity(orderDto)));
    }
}
