package kz.Bootcamp.Trip.mapper;

import kz.Bootcamp.Trip.dto.RequestDto;
import kz.Bootcamp.Trip.model.Request;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RequestMapper {

    RequestDto toDto(Request request);
    Request toEntity(RequestDto requestDto);

    List<RequestDto> toDtoList(List<Request> requestList);
    List<Request> toEntityList(List<RequestDto> requestDtoList);
}
