package com.cinar.flightsearch.service;

import com.cinar.flightsearch.model.Airport;
import com.cinar.flightsearch.model.Flight;
import com.cinar.flightsearch.util.FlightSearchCriteria;
import com.cinar.flightsearch.util.FlightSearchResult;
import com.cinar.flightsearch.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Optional<Flight> getFlightById(Long id) {
        return flightRepository.findById(id);
    }

    public Flight createFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public Optional<Flight> updateFlight(Long id, Flight updatedFlight) {
        Optional<Flight> flight = flightRepository.findById(id);
        if (flight.isPresent()) {
            updatedFlight.setId(id);
            Flight savedFlight = flightRepository.save(updatedFlight);
            return Optional.of(savedFlight);
        } else {
            return Optional.empty();
        }
    }

    public boolean deleteFlight(Long id) {
        Optional<Flight> flight = flightRepository.findById(id);
        if (flight.isPresent()) {
            flightRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public List<FlightSearchResult> searchFlights(FlightSearchCriteria searchCriteria) {
        List<FlightSearchResult> searchResults = new ArrayList<>();

        List<Flight> outboundFlights = flightRepository.findByDepartureAirportAndArrivalAirportAndDepartureDateTimeBetween(
                searchCriteria.getDepartureAirport(),
                searchCriteria.getArrivalAirport(),
                searchCriteria.getDepartureDateTime(),
                searchCriteria.getArrivalDateTime()
        );

        for (Flight outboundFlight : outboundFlights) {
            FlightSearchResult searchResult = new FlightSearchResult();
            searchResult.setOutboundFlight(outboundFlight);

            if (searchCriteria.getArrivalDateTime() != null) {
                // Round-trip search
                List<Flight> returnFlights = flightRepository.findByDepartureAirportAndArrivalAirportAndDepartureDateTimeBetween(
                        searchCriteria.getDepartureAirport(),
                        searchCriteria.getArrivalAirport(),
                        searchCriteria.getArrivalDateTime().plusMinutes(30),
                        searchCriteria.getDepartureDateTime().plusDays(1)
                );

                if (!returnFlights.isEmpty()) {
                    searchResult.setReturnFlight(returnFlights.get(0));
                }
            }

            searchResults.add(searchResult);
        }

        return searchResults;
    }

    public List<Flight> searchFlightsOneWay(FlightSearchCriteria searchCriteria) {
        return flightRepository.findByDepartureAirportAndArrivalAirportAndDepartureDateTimeBetweenAndArrivalDateTimeIsNull(
                searchCriteria.getDepartureAirport(),
                searchCriteria.getArrivalAirport(),
                searchCriteria.getDepartureDateTime(),
                searchCriteria.getArrivalDateTime()
        );
    }


}
