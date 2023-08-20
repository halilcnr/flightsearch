package com.cinar.flightsearch.service;

import com.cinar.flightsearch.model.Airport;
import com.cinar.flightsearch.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportService {
    @Autowired
    private AirportRepository airportRepository;

    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    public Optional<Airport> getAirportById(Long id) {
        return airportRepository.findById(id);
    }

    public Airport createAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    public Optional<Airport> updateAirport(Long id, Airport updatedAirport) {
        Optional<Airport> airport = airportRepository.findById(id);
        if (airport.isPresent()) {
            updatedAirport.setId(id);
            Airport savedAirport = airportRepository.save(updatedAirport);
            return Optional.of(savedAirport);
        } else {
            return Optional.empty();
        }
    }

    public boolean deleteAirport(Long id) {
        Optional<Airport> airport = airportRepository.findById(id);
        if (airport.isPresent()) {
            airportRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
