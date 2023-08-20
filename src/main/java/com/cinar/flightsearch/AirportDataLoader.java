package com.cinar.flightsearch;

import com.cinar.flightsearch.model.Airport;
import com.cinar.flightsearch.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AirportDataLoader implements CommandLineRunner {

    private final AirportRepository airportRepository;

    @Autowired
    public AirportDataLoader(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadAirportData();
    }

    private void loadAirportData() {
        // Create and save Airport entities
        airportRepository.save(new Airport("Istanbul"));
        airportRepository.save(new Airport("Ankara"));
        airportRepository.save(new Airport("Bursa"));
        airportRepository.save(new Airport("Bodrum"));
        airportRepository.save(new Airport("Kars"));
        airportRepository.save(new Airport("Van"));
        airportRepository.save(new Airport("Nigde"));
        airportRepository.save(new Airport("Aksaray"));
        airportRepository.save(new Airport("Urfa"));
        airportRepository.save(new Airport("Katowice"));
        airportRepository.save(new Airport("Madrid"));
        airportRepository.save(new Airport("Moscow"));
        airportRepository.save(new Airport("Diyarbakir"));
        airportRepository.save(new Airport("Amsterdam"));
        airportRepository.save(new Airport("London"));
        airportRepository.save(new Airport("Manchester"));
        airportRepository.save(new Airport("Casablanca"));
        airportRepository.save(new Airport("Dubai"));
        airportRepository.save(new Airport("Doha"));
        airportRepository.save(new Airport("Warsaw"));
        airportRepository.save(new Airport("Krakow"));
        airportRepository.save(new Airport("Milano"));
        airportRepository.save(new Airport("Napoli"));
        airportRepository.save(new Airport("Venice"));
        airportRepository.save(new Airport("Berlin"));
        airportRepository.save(new Airport("Dublin"));
        airportRepository.save(new Airport("Munchen"));
        airportRepository.save(new Airport("Dortmund"));
        airportRepository.save(new Airport("Katowice"));
        airportRepository.save(new Airport("Antalya"));

        System.out.println("Airport data loaded.");
    }
}
