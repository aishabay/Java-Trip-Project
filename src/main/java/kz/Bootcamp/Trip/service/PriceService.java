package kz.Bootcamp.Trip.service;

import kz.Bootcamp.Trip.dto.PriceDto;
import kz.Bootcamp.Trip.model.Price;

import java.util.List;

public interface PriceService {
    List<Price> getAllPricesByTourId(Long id);
    Price getPriceById(Long id);

    List<PriceDto> getAllPricesByTourIdDto(Long id);
    PriceDto getPriceByIdDto(Long id);
}
