package kz.Bootcamp.Trip.service.impl;

import kz.Bootcamp.Trip.dto.PriceDto;
import kz.Bootcamp.Trip.mapper.PriceMapper;
import kz.Bootcamp.Trip.model.Price;
import kz.Bootcamp.Trip.model.Tour;
import kz.Bootcamp.Trip.repository.PriceRepository;
import kz.Bootcamp.Trip.service.PriceService;
import kz.Bootcamp.Trip.service.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {
    private final TourService tourService;
    private final PriceRepository priceRepository;
    private final PriceMapper priceMapper;

    @Override
    public List<Price> getAllPricesByTourId(Long id) {
        Tour tour = tourService.getTour(id);
        return priceRepository.findAllByTour(tour);
    }

    @Override
    public Price getPriceById(Long id) {
        return priceRepository.findById(id).orElseThrow();
    }

    @Override
    public List<PriceDto> getAllPricesByTourIdDto(Long id) {
        return priceMapper.toDtoList(getAllPricesByTourId(id));
    }

    @Override
    public PriceDto getPriceByIdDto(Long id) {
        return priceMapper.toDto(getPriceById(id));
    }
}
