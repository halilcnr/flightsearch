package com.cinar.flightsearch.controller;

import com.cinar.flightsearch.model.Airport;
import com.cinar.flightsearch.model.Flight;
import com.cinar.flightsearch.service.ExternalAPIService;
import com.cinar.flightsearch.util.FlightSearchCriteria;
import com.cinar.flightsearch.util.FlightSearchResult;
import com.cinar.flightsearch.repository.FlightRepository;
import com.cinar.flightsearch.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.format.DateTimeFormatter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flights")
public class FlightController {
    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private FlightService flightService;

    @Autowired
    private ExternalAPIService externalAPIService;

    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights() {
        List<Flight> flights = flightRepository.findAll();
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long id) {
        Optional<Flight> flight = flightRepository.findById(id);
        return flight.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight) {
        Flight newFlight = flightRepository.save(flight);
        return new ResponseEntity<>(newFlight, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable Long id, @RequestBody Flight updatedFlight) {
        Optional<Flight> flight = flightRepository.findById(id);
        if (flight.isPresent()) {
            updatedFlight.setId(id);
            Flight savedFlight = flightRepository.save(updatedFlight);
            return new ResponseEntity<>(savedFlight, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
        Optional<Flight> flight = flightRepository.findById(id);
        if (flight.isPresent()) {
            flightRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/search")
    public ResponseEntity<List<FlightSearchResult>> searchFlights(@RequestBody FlightSearchCriteria searchCriteria) {
        List<FlightSearchResult> searchResults = flightService.searchFlights(searchCriteria);
        return new ResponseEntity<>(searchResults, HttpStatus.OK);
    }

    @PostMapping("/search/oneway")
    public ResponseEntity<List<Flight>> searchOneWayFlights(@RequestBody FlightSearchCriteria searchCriteria) {
        List<Flight> flights = flightService.searchFlightsOneWay(searchCriteria);
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }



    @GetMapping("/apisearch")
    public ResponseEntity<List<Flight>> fetchFlightInformation(@RequestBody FlightSearchCriteria searchCriteria) {

        LocalDateTime departureDateTime = searchCriteria.getDepartureDateTime();
        LocalDateTime arrivalDateTime= searchCriteria.getArrivalDateTime();

        List<Flight> flights = Collections.singletonList(externalAPIService.fetchFlightInformation(
                searchCriteria.getDepartureAirport(),
                searchCriteria.getArrivalAirport(),
                departureDateTime,
                arrivalDateTime
        ));

        return new ResponseEntity<>(flights, HttpStatus.OK);
    }


}
