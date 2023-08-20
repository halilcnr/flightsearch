package com.cinar.flightsearch.repository;

import com.cinar.flightsearch.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Long> {
}