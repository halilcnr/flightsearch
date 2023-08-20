package com.cinar.flightsearch.service;

import com.cinar.flightsearch.model.Airport;
import com.cinar.flightsearch.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class ExternalAPIService {

    private final RestTemplate restTemplate;

    @Value("${externalApi.baseUrl}")
    private String externalApiBaseUrl;

    @Value("${externalApi.apiKey}")
    private String apiKey;

    @Autowired
    public ExternalAPIService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Flight fetchFlightInformation(Airport departureAirport, Airport arrivalAirport, LocalDateTime departureDateTime, LocalDateTime arrivalDateTime) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-RapidAPI-Key", apiKey);
        headers.set("X-RapidAPI-Host", "timetable-lookup.p.rapidapi.com");

        String requestUrl = buildRequestUrl(
                departureAirport.getCity(),
                arrivalAirport.getCity(),
                departureDateTime.toString(),
                arrivalDateTime.toString()
        );

        return restTemplate.getForObject(requestUrl, Flight.class);
    }

    private String buildRequestUrl(String departureAirport, String arrivalAirport, String departureDateTime, String arrivalDateTime) {
        return externalApiBaseUrl + "/" + departureAirport + "/" + arrivalAirport + "/" + departureDateTime + "/" + arrivalDateTime + "/";
    }
}
