package kz.Bootcamp.Trip.api;

import kz.Bootcamp.Trip.dto.RequestDto;
import kz.Bootcamp.Trip.dto.TourDto;
import kz.Bootcamp.Trip.model.Request;
import kz.Bootcamp.Trip.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/request")
@RequiredArgsConstructor
public class RequestController {

    private final RequestService requestService;

    @GetMapping
    public List<RequestDto> getAllRequests(){
        return requestService.getAllRequestsDto();
    }

    @GetMapping(value = "{id}")
    public RequestDto getRequestById(@PathVariable(name = "id") Long id){
        return requestService.getRequestDto(id);
    }

    @PostMapping
    public RequestDto addRequest(@RequestBody RequestDto requestDto){
        return requestService.addRequestDto(requestDto);
    }

}
