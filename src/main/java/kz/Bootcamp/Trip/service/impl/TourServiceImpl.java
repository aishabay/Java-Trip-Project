package kz.Bootcamp.Trip.service.impl;

import kz.Bootcamp.Trip.dto.TourDto;
import kz.Bootcamp.Trip.mapper.TourMapper;
import kz.Bootcamp.Trip.model.Tour;
import kz.Bootcamp.Trip.repository.TourRepository;
import kz.Bootcamp.Trip.service.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TourServiceImpl implements TourService {

    private final TourRepository tourRepository;
//    private final UserMapper userMapper;
    private final TourMapper tourMapper;

    @Override
    public List<Tour> getAllTours() {
        return tourRepository.findAll();
    }

    @Override
    public Tour addTour(Tour tour) {
        return tourRepository.save(tour);
    }

    @Override
    public Tour getTour(Long id) {
        return tourRepository.findById(id).orElseThrow();
    }

    @Override
    public List<String> getTourDesc(Long id) {
        Tour tour = tourRepository.findById(id).orElseThrow();
        String desc_str = tour.getDescription();
        List<String> desc_list = Arrays.asList(desc_str.split("\n"));

        return desc_list;
    }

    @Override
    public List<Tour> getToursSearch(String key, String order) {
        List<Tour> tours = null;

        if(order != null){
            if(order.equals("desc")){
//                tours = tourRepository.findAllByNameIsLikeOrderByDurationDesc("%"+key.toLowerCase()+"%");
                tours = tourRepository.searchToursDurationDesc("%"+key.toLowerCase()+"%");
            }
        }
        if(tours==null){
//            tours = tourRepository.findAllByNameIsLikeOrderByDurationAsc("%"+key.toLowerCase()+"%");
            tours = tourRepository.searchToursDurationAsc("%"+key.toLowerCase()+"%");
        }

        return tours;
    }

    @Override
    public List<TourDto> getAllToursDto() {
        return tourMapper.toDtoList(getAllTours());
    }

    @Override
    public TourDto addTourDto(TourDto tourDto) {
        return tourMapper.toDto(addTour(tourMapper.toEntity(tourDto)));
    }

    @Override
    public TourDto getTourDto(Long id) {
        return tourMapper.toDto(getTour(id));
    }
}
