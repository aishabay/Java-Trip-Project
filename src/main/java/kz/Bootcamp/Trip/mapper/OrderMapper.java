package kz.Bootcamp.Trip.mapper;

import kz.Bootcamp.Trip.dto.OrderDto;
import kz.Bootcamp.Trip.model.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDto toDto(Order order);
    Order toEntity(OrderDto orderDto);

    List<OrderDto> toDtoList(List<Order> orderList);
    List<Order> toEntityList(List<OrderDto> orderDtoList);
}
