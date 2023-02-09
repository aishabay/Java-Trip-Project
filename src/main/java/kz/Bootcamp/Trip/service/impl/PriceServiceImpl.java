package kz.Bootcamp.Trip.service.impl;

import kz.Bootcamp.Trip.dto.PriceDto;
import kz.Bootcamp.Trip.mapper.PriceMapper;
import kz.Bootcamp.Trip.model.Item;
import kz.Bootcamp.Trip.model.Price;
import kz.Bootcamp.Trip.model.Tour;
import kz.Bootcamp.Trip.repository.PriceRepository;
import kz.Bootcamp.Trip.service.ItemService;
import kz.Bootcamp.Trip.service.PriceService;
import kz.Bootcamp.Trip.service.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public Price getPriceByTripIdAndNameAndPrice(Long tripId, String name, int price) {
        return priceRepository.findByNameAndPriceAndTourId(name,price,tripId);
    }

    @Override
    public Price addPrice(Long tripId, Price price) {
        Tour tour = tourService.getTour(tripId);
        price.setTour(tour);
        return priceRepository.save(price);
    }

    @Override
    public Price updatePrice(Price price) {
        return priceRepository.save(price);
    }

    @Override
    public void deletePrice(Long id) {
        Price price = getPriceById(id);
        List<Item> items = new ArrayList<>();
        price.setItems(items);
        updatePrice(price);
        priceRepository.deleteById(id);
    }

    @Override
    public void deletePricesByTourId(Long id) {
        List<Item> items = new ArrayList<>();
        List<Price> prices = getAllPricesByTourId(id);
        for(int i=0;i< prices.size();i++){
            prices.get(i).setItems(items);
            priceRepository.deleteById(prices.get(i).getId());
        }
    }
}
