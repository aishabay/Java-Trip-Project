package kz.Bootcamp.Trip;

import kz.Bootcamp.Trip.dto.RequestDto;
import kz.Bootcamp.Trip.mapper.RequestMapper;
import kz.Bootcamp.Trip.model.Request;
import kz.Bootcamp.Trip.service.RequestService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class TripApplicationTests {

	@Autowired
	private RequestMapper requestMapper;

	@Autowired
	private RequestService requestService;

	@Test
	void contextLoads() {
	}

	@Test
	void givenRequest_whenRequestMapper_thenReturnRequestDto(){
		Request request = new Request();
		request.setId(1L);
		request.setName("Some name");
		request.setEmail("some@gmail.com");
		request.setNumber(1234);
		LocalDateTime currentTime = LocalDateTime.now();
		request.setPostDate(currentTime);
		request.setMessage("Some message");

		RequestDto requestDto = requestMapper.toDto(request);

		Assertions.assertEquals(request.getId(), requestDto.getId());
		Assertions.assertEquals(request.getName(), requestDto.getName());
		Assertions.assertEquals(request.getEmail(), requestDto.getEmail());
		Assertions.assertEquals(request.getNumber(), requestDto.getNumber());
		Assertions.assertEquals(request.getMessage(), requestDto.getMessage());
		Assertions.assertEquals(request.getPostDate(), requestDto.getPostDate());
	}

	@Test
	void givenTwoRequests_whenGetAllRequests_thenReturnSizeTwo(){
		Request request = new Request();
		request.setId(1L);
		request.setName("Some name");
		request.setEmail("some@gmail.com");
		request.setNumber(1234);
		LocalDateTime currentTime = LocalDateTime.now();
		request.setPostDate(currentTime);
		request.setMessage("Some message");

		requestService.addRequest(request);

		Request request2 = new Request();
		request2.setId(1L);
		request2.setName("Some name 2");
		request2.setEmail("some2@gmail.com");
		request2.setNumber(12341234);
		LocalDateTime currentTime2 = LocalDateTime.now();
		request2.setPostDate(currentTime2);
		request2.setMessage("Some message 2");

		requestService.addRequest(request2);

		List<Request> requests = requestService.getAllRequests();

		Assertions.assertEquals(2,requests.size());

		for(Request req : requests){
			requestService.deleteRequest(req.getId());
		}
	}

	@Test
	void givenRequest_whenAddRequestAndGetRequest_thenReturnRequest(){
		Request request = new Request();
//		request.setId(1L);
		request.setName("Some name");
		request.setEmail("some@gmail.com");
		request.setNumber(1234);
		LocalDateTime currentTime = LocalDateTime.now();
		request.setPostDate(currentTime);
		request.setMessage("Some message");

		Request newRequest = requestService.addRequest(request);

		Assertions.assertNotNull(newRequest);
		Assertions.assertNotNull(newRequest.getId());

		Request checkRequest = requestService.getRequest(newRequest.getId());
		Assertions.assertNotNull(checkRequest);
		Assertions.assertEquals(checkRequest.getId(),newRequest.getId());

		requestService.deleteRequest(newRequest.getId());
	}

	@Test
	void givenRequest_whenUpdateRequest_thenReturnUpdatedRequest(){
		Request request = new Request();
//		request.setId(1L);
		request.setName("Some name");
		request.setEmail("some@gmail.com");
		request.setNumber(1234);
		LocalDateTime currentTime = LocalDateTime.now();
		request.setPostDate(currentTime);
		request.setMessage("Some message");

		Request newRequest = requestService.addRequest(request);

		Assertions.assertNotNull(newRequest);
		Assertions.assertNotNull(newRequest.getId());

		request.setId(newRequest.getId());
		request.setName("Some name 2");
		request.setEmail("some2@gmail.com");
		request.setNumber(12341234);
		currentTime = LocalDateTime.now();
		request.setPostDate(currentTime);
		request.setMessage("Some message 2");

		Request updatedNewRequest = requestService.updateRequest(request);
		Assertions.assertNotNull(updatedNewRequest);
		Assertions.assertEquals(updatedNewRequest.getId(),newRequest.getId());
		Assertions.assertEquals(updatedNewRequest.getName(),newRequest.getName());
		Assertions.assertEquals(updatedNewRequest.getEmail(),newRequest.getEmail());
		Assertions.assertEquals(updatedNewRequest.getNumber(),newRequest.getNumber());
		Assertions.assertEquals(updatedNewRequest.getMessage(),newRequest.getMessage());
		Assertions.assertEquals(updatedNewRequest.getPostDate(),newRequest.getPostDate());

		requestService.deleteRequest(newRequest.getId());
	}

	@Test
	void givenRequest_whenDeleteRequest_thenReturnTrue(){
		Request request = new Request();
		request.setName("Some name");
		request.setEmail("some@gmail.com");
		request.setNumber(1234);
		LocalDateTime currentTime = LocalDateTime.now();
		request.setPostDate(currentTime);
		request.setMessage("Some message");

		Request newRequest = requestService.addRequest(request);
		Assertions.assertNotNull(newRequest);
		Assertions.assertNotNull(newRequest.getId());

		Boolean isRequestDeleted = requestService.deleteRequest(newRequest.getId());
		try{
			Request checkDeletedRequest = requestService.getRequest(newRequest.getId());
			Assertions.assertNull(checkDeletedRequest);
		}catch (Exception e){
			Assertions.assertTrue(isRequestDeleted);
		}
	}

}
