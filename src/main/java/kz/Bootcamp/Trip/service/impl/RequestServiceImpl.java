package kz.Bootcamp.Trip.service.impl;

import kz.Bootcamp.Trip.dto.RequestDto;
import kz.Bootcamp.Trip.mapper.RequestMapper;
import kz.Bootcamp.Trip.model.Request;
import kz.Bootcamp.Trip.repository.RequestRepository;
import kz.Bootcamp.Trip.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {
    private final RequestRepository requestRepository;
    private final RequestMapper requestMapper;
    @Override
    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    @Override
    public Request addRequest(Request request) {
        return requestRepository.save(request);
    }

    @Override
    public Request getRequest(Long id) {
        return requestRepository.findById(id).orElseThrow();
    }

    @Override
    public Request updateRequest(Request request) {
        return requestRepository.save(request);
    }

    @Override
    public Boolean deleteRequest(Long id) {
        Request request = getRequest(id);
        if(request!=null){
            requestRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<RequestDto> getAllRequestsDto() {
        return requestMapper.toDtoList(getAllRequests());
    }
    @Override
    public RequestDto getRequestDto(Long id) {
        return requestMapper.toDto(getRequest(id));
    }

    @Override
    public RequestDto addRequestDto(RequestDto requestDto) {
        return requestMapper.toDto(addRequest(requestMapper.toEntity(requestDto)));
    }
}
