package kz.Bootcamp.Trip.service;

import kz.Bootcamp.Trip.dto.RequestDto;
import kz.Bootcamp.Trip.model.Request;

import java.util.List;

public interface RequestService {
    List<Request> getAllRequests();
    Request getRequest(Long id);
    Request addRequest(Request request);

    Request updateRequest(Request request);

    Boolean deleteRequest(Long id);

    List<RequestDto> getAllRequestsDto();
    RequestDto addRequestDto(RequestDto requestDto);
    RequestDto getRequestDto(Long id);
}
