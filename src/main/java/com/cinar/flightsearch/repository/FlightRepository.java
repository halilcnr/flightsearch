package com.cinar.flightsearch.repository;

import com.cinar.flightsearch.model.Airport;
import com.cinar.flightsearch.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findByDepartureAirportAndArrivalAirportAndDepartureDateTimeBetween(
            Airport departureAirport, Airport arrivalAirport, LocalDateTime startDateTime, LocalDateTime endDateTime);

    List<Flight> findByArrivalAirport_CityAndDepartureAirport_CityAndDepartureDateTimeBetween(
            Airport departureAirport, Airport arrivalAirport, LocalDateTime startDateTime, LocalDateTime endDateTime);
    List<Flight> findByDepartureAirportAndArrivalAirportAndDepartureDateTimeBetweenAndArrivalDateTimeIsNull(
            Airport departureAirport, Airport arrivalAirport,
            LocalDateTime departureDateTime, LocalDateTime arrivalDateTime);



}
