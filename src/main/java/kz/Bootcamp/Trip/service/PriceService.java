package kz.Bootcamp.Trip.service;

import kz.Bootcamp.Trip.dto.PriceDto;
import kz.Bootcamp.Trip.model.Item;
import kz.Bootcamp.Trip.model.Price;

import java.util.List;

public interface PriceService {
    List<Price> getAllPricesByTourId(Long id);
    Price getPriceById(Long id);

    List<PriceDto> getAllPricesByTourIdDto(Long id);
    PriceDto getPriceByIdDto(Long id);

    Price addPrice(Long tripId, Price price);

    Price updatePrice(Price price);

    Price getPriceByTripIdAndNameAndPrice(Long tripId, String name, int price);

    void deletePrice(Long id);

    void deletePricesByTourId(Long id);
}
