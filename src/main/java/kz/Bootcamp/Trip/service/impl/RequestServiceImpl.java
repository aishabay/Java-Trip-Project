package kz.Bootcamp.Trip.service.impl;

import kz.Bootcamp.Trip.model.Request;
import kz.Bootcamp.Trip.repository.RequestRepository;
import kz.Bootcamp.Trip.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {
    private final RequestRepository requestRepository;

    @Override
    public Request addRequest(Request request) {
        return requestRepository.save(request);
    }
}
