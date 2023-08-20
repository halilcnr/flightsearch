package com.cinar.flightsearch;

import com.cinar.flightsearch.model.Airport;
import com.cinar.flightsearch.model.Flight;
import com.cinar.flightsearch.repository.AirportRepository;
import com.cinar.flightsearch.repository.FlightRepository;
import org.json.JSONArray; // Add this import
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class FlightDataLoader implements CommandLineRunner {

    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;

    @Autowired
    public FlightDataLoader(FlightRepository flightRepository, AirportRepository airportRepository) {
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadFlightData();
    }

    private void loadFlightData() {
        String jsonData = "[" +
                "    {\n" +
                "        \"departureAirport\": {\"id\": 2},\n" +
                "        \"arrivalAirport\": {\"id\": 6},\n" +
                "        \"departureDateTime\": \"2023-08-16T10:00:00\",\n" +
                "        \"arrivalDateTime\": \"2023-08-16T12:00:00\",\n" +
                "        \"price\": 400.0\n" +
                "    },\n" +
                "    {\n" +
                "        \"departureAirport\": {\"id\": 6},\n" +
                "        \"arrivalAirport\": {\"id\": 2},\n" +
                "        \"departureDateTime\": \"2023-08-16T12:30:00\",\n" +
                "        \"arrivalDateTime\": \"2023-08-16T14:30:00\",\n" +
                "        \"price\": 400.0\n" +
                "    },\n" +
                "    {\n" +
                "        \"departureAirport\": {\"id\": 3},\n" +
                "        \"arrivalAirport\": {\"id\": 7},\n" +
                "        \"departureDateTime\": \"2023-08-17T09:15:00\",\n" +
                "        \"arrivalDateTime\": \"2023-08-17T11:30:00\",\n" +
                "        \"price\": 320.0\n" +
                "    },\n" +
                "    {\n" +
                "        \"departureAirport\": {\"id\": 7},\n" +
                "        \"arrivalAirport\": {\"id\": 3},\n" +
                "        \"departureDateTime\": \"2023-08-17T12:00:00\",\n" +
                "        \"arrivalDateTime\": \"2023-08-17T14:15:00\",\n" +
                "        \"price\": 320.0\n" +
                "    },\n" +
                "    {\n" +
                "        \"departureAirport\": {\"id\": 4},\n" +
                "        \"arrivalAirport\": {\"id\": 8},\n" +
                "        \"departureDateTime\": \"2023-08-18T14:30:00\",\n" +
                "        \"arrivalDateTime\": \"2023-08-18T16:45:00\",\n" +
                "        \"price\": 490.0\n" +
                "    },\n" +
                "    {\n" +
                "        \"departureAirport\": {\"id\": 8},\n" +
                "        \"arrivalAirport\": {\"id\": 4},\n" +
                "        \"departureDateTime\": \"2023-08-18T17:15:00\",\n" +
                "        \"arrivalDateTime\": \"2023-08-18T19:30:00\",\n" +
                "        \"price\": 490.0\n" +
                "    },\n" +
                "    {\n" +
                "        \"departureAirport\": {\"id\": 5},\n" +
                "        \"arrivalAirport\": {\"id\": 9},\n" +
                "        \"departureDateTime\": \"2023-08-19T10:45:00\",\n" +
                "        \"arrivalDateTime\": \"2023-08-19T13:00:00\",\n" +
                "        \"price\": 370.0\n" +
                "    },\n" +
                "    {\n" +
                "        \"departureAirport\": {\"id\": 9},\n" +
                "        \"arrivalAirport\": {\"id\": 5},\n" +
                "        \"departureDateTime\": \"2023-08-19T13:30:00\",\n" +
                "        \"arrivalDateTime\": \"2023-08-19T15:45:00\",\n" +
                "        \"price\": 370.0\n" +
                "    },\n" +
                "    {\n" +
                "        \"departureAirport\": {\"id\": 6},\n" +
                "        \"arrivalAirport\": {\"id\": 10},\n" +
                "        \"departureDateTime\": \"2023-08-20T08:00:00\",\n" +
                "        \"arrivalDateTime\": \"2023-08-20T10:15:00\",\n" +
                "        \"price\": 290.0\n" +
                "    },\n" +
                "    {\n" +
                "        \"departureAirport\": {\"id\": 10},\n" +
                "        \"arrivalAirport\": {\"id\": 6},\n" +
                "        \"departureDateTime\": \"2023-08-20T10:45:00\",\n" +
                "        \"arrivalDateTime\": \"2023-08-20T13:00:00\",\n" +
                "        \"price\": 290.0\n" +
                "    },\n" +
                "    {\n" +
                "        \"departureAirport\": {\"id\": 7},\n" +
                "        \"arrivalAirport\": {\"id\": 11},\n" +
                "        \"departureDateTime\": \"2023-08-21T11:30:00\",\n" +
                "        \"arrivalDateTime\": \"2023-08-21T13:45:00\",\n" +
                "        \"price\": 420.0\n" +
                "    },\n" +
                "    {\n" +
                "        \"departureAirport\": {\"id\": 11},\n" +
                "        \"arrivalAirport\": {\"id\": 7},\n" +
                "        \"departureDateTime\": \"2023-08-21T14:15:00\",\n" +
                "        \"arrivalDateTime\": \"2023-08-21T16:30:00\",\n" +
                "        \"price\": 420.0\n" +
                "    },\n" +
                "    {\n" +
                "        \"departureAirport\": {\"id\": 8},\n" +
                "        \"arrivalAirport\": {\"id\": 12},\n" +
                "        \"departureDateTime\": \"2023-08-22T10:00:00\",\n" +
                "        \"arrivalDateTime\": \"2023-08-22T12:15:00\",\n" +
                "        \"price\": 380.0\n" +
                "    },\n" +
                "    {\n" +
                "        \"departureAirport\": {\"id\": 12},\n" +
                "        \"arrivalAirport\": {\"id\": 8},\n" +
                "        \"departureDateTime\": \"2023-08-22T12:45:00\",\n" +
                "        \"arrivalDateTime\": \"2023-08-22T15:00:00\",\n" +
                "        \"price\": 380.0\n" +
                "    },\n" +
                "    {\n" +
                "        \"departureAirport\": {\"id\": 9},\n" +
                "        \"arrivalAirport\": {\"id\": 13},\n" +
                "        \"departureDateTime\": \"2023-08-23T15:30:00\",\n" +
                "        \"arrivalDateTime\": \"2023-08-23T17:45:00\",\n" +
                "        \"price\": 420.0\n" +
                "    },\n" +
                "    {\n" +
                "        \"departureAirport\": {\"id\": 13},\n" +
                "        \"arrivalAirport\": {\"id\": 9},\n" +
                "        \"departureDateTime\": \"2023-08-23T18:15:00\",\n" +
                "        \"arrivalDateTime\": \"2023-08-23T20:30:00\",\n" +
                "        \"price\": 420.0\n" +
                "    },\n" +
                "    {\n" +
                "        \"departureAirport\": {\"id\": 10},\n" +
                "        \"arrivalAirport\": {\"id\": 14},\n" +
                "        \"departureDateTime\": \"2023-08-24T13:45:00\",\n" +
                "        \"arrivalDateTime\": \"2023-08-24T16:00:00\",\n" +
                "        \"price\": 290.0\n" +
                "    },\n" +
                "    {\n" +
                "        \"departureAirport\": {\"id\": 14},\n" +
                "        \"arrivalAirport\": {\"id\": 10},\n" +
                "        \"departureDateTime\": \"2023-08-24T16:30:00\",\n" +
                "        \"arrivalDateTime\": \"2023-08-24T18:45:00\",\n" +
                "        \"price\": 290.0\n" +
                "    },\n" +
                "    {\n" +
                "        \"departureAirport\": {\"id\": 11},\n" +
                "        \"arrivalAirport\": {\"id\": 15},\n" +
                "        \"departureDateTime\": \"2023-08-25T10:00:00\",\n" +
                "        \"arrivalDateTime\": \"2023-08-25T12:15:00\",\n" +
                "        \"price\": 550.0\n" +
                "    },\n" +
                "    {\n" +
                "        \"departureAirport\": {\"id\": 15},\n" +
                "        \"arrivalAirport\": {\"id\": 11},\n" +
                "        \"departureDateTime\": \"2023-08-25T12:45:00\",\n" +
                "        \"arrivalDateTime\": \"2023-08-25T15:00:00\",\n" +
                "        \"price\": 550.0\n" +
                "    },\n" +
                "    {\n" +
                "        \"departureAirport\": {\"id\": 12},\n" +
                "        \"arrivalAirport\": {\"id\": 16},\n" +
                "        \"departureDateTime\": \"2023-08-26T15:30:00\",\n" +
                "        \"arrivalDateTime\": \"2023-08-26T17:45:00\",\n" +
                "        \"price\": 410.0\n" +
                "    },\n" +
                "    {\n" +
                "        \"departureAirport\": {\"id\": 16},\n" +
                "        \"arrivalAirport\": {\"id\": 12},\n" +
                "        \"departureDateTime\": \"2023-08-26T18:15:00\",\n" +
                "        \"arrivalDateTime\": \"2023-08-26T20:30:00\",\n" +
                "        \"price\": 410.0\n" +
                "    },\n" +
                "    {\n" +
                "        \"departureAirport\": {\"id\": 13},\n" +
                "        \"arrivalAirport\": {\"id\": 17},\n" +
                "        \"departureDateTime\": \"2023-08-27T11:45:00\",\n" +
                "        \"arrivalDateTime\": \"2023-08-27T14:00:00\",\n" +
                "        \"price\": 320.0\n" +
                "    },\n" +
                "    {\n" +
                "        \"departureAirport\": {\"id\": 17},\n" +
                "        \"arrivalAirport\": {\"id\": 13},\n" +
                "        \"departureDateTime\": \"2023-08-27T14:30:00\",\n" +
                "        \"arrivalDateTime\": \"2023-08-27T16:45:00\",\n" +
                "        \"price\": 320.0\n" +
                "    },\n" +
                "    {\n" +
                "        \"departureAirport\": {\"id\": 14},\n" +
                "        \"arrivalAirport\": {\"id\": 18},\n" +
                "        \"departureDateTime\": \"2023-08-28T16:00:00\",\n" +
                "        \"arrivalDateTime\": \"2023-08-28T18:15:00\",\n" +
                "        \"price\": 490.0\n" +
                "    },\n" +
                "    {\n" +
                "        \"departureAirport\": {\"id\": 18},\n" +
                "        \"arrivalAirport\": {\"id\": 14},\n" +
                "        \"departureDateTime\": \"2023-08-28T18:45:00\",\n" +
                "        \"arrivalDateTime\": \"2023-08-28T21:00:00\",\n" +
                "        \"price\": 490.0\n" +
                "    },\n" +
                "    {\n" +
                "        \"departureAirport\": {\"id\": 15},\n" +
                "        \"arrivalAirport\": {\"id\": 19},\n" +
                "        \"departureDateTime\": \"2023-08-29T10:30:00\",\n" +
                "        \"arrivalDateTime\": \"2023-08-29T12:45:00\",\n" +
                "        \"price\": 370.0\n" +
                "    },\n" +
                "    {\n" +
                "        \"departureAirport\": {\"id\": 19},\n" +
                "        \"arrivalAirport\": {\"id\": 15},\n" +
                "        \"departureDateTime\": \"2023-08-29T13:15:00\",\n" +
                "        \"arrivalDateTime\": \"2023-08-29T15:30:00\",\n" +
                "        \"price\": 370.0\n" +
                "    },\n" +
                "    {\n" +
                "        \"departureAirport\": {\"id\": 16},\n" +
                "        \"arrivalAirport\": {\"id\": 20},\n" +
                "        \"departureDateTime\": \"2023-08-30T14:45:00\",\n" +
                "        \"arrivalDateTime\": \"2023-08-30T17:00:00\",\n" +
                "        \"price\": 290.0\n" +
                "    },\n" +
                "    {\n" +
                "        \"departureAirport\": {\"id\": 20},\n" +
                "        \"arrivalAirport\": {\"id\": 16},\n" +
                "        \"departureDateTime\": \"2023-08-30T17:30:00\",\n" +
                "        \"arrivalDateTime\": \"2023-08-30T19:45:00\",\n" +
                "        \"price\": 290.0\n" +
                "    },\n" +
                "    {\n" +
                "        \"departureAirport\": {\"id\": 17},\n" +
                "        \"arrivalAirport\": {\"id\": 21},\n" +
                "        \"departureDateTime\": \"2023-08-31T12:15:00\",\n" +
                "        \"arrivalDateTime\": \"2023-08-31T14:30:00\",\n" +
                "        \"price\": 420.0\n" +
                "    },\n" +
                "    {\n" +
                "        \"departureAirport\": {\"id\": 21},\n" +
                "        \"arrivalAirport\": {\"id\": 17},\n" +
                "        \"departureDateTime\": \"2023-08-31T15:00:00\",\n" +
                "        \"arrivalDateTime\": \"2023-08-31T17:15:00\",\n" +
                "        \"price\": 420.0\n" +
                "    },\n" +
                "    {\n" +
                "        \"departureAirport\": {\"id\": 18},\n" +
                "        \"arrivalAirport\": {\"id\": 22},\n" +
                "        \"departureDateTime\": \"2023-09-01T09:30:00\",\n" +
                "        \"arrivalDateTime\": \"2023-09-01T11:45:00\",\n" +
                "        \"price\": 380.0\n" +
                "    },\n" +
                "    {\n" +
                "        \"departureAirport\": {\"id\": 22},\n" +
                "        \"arrivalAirport\": {\"id\": 18},\n" +
                "        \"departureDateTime\": \"2023-09-01T12:15:00\",\n" +
                "        \"arrivalDateTime\": \"2023-09-01T14:30:00\",\n" +
                "        \"price\": 380.0\n" +
                "    },\n" +
                "    {\n" +
                "        \"departureAirport\": {\"id\": 19},\n" +
                "        \"arrivalAirport\": {\"id\": 23},\n" +
                "        \"departureDateTime\": \"2023-09-02T14:00:00\",\n" +
                "        \"arrivalDateTime\": \"2023-09-02T16:15:00\",\n" +
                "        \"price\": 420.0\n" +
                "    },\n" +
                "    {\n" +
                "        \"departureAirport\": {\"id\": 23},\n" +
                "        \"arrivalAirport\": {\"id\": 19},\n" +
                "        \"departureDateTime\": \"2023-09-02T16:45:00\",\n" +
                "        \"arrivalDateTime\": \"2023-09-02T19:00:00\",\n" +
                "        \"price\": 420.0\n" +
                "    },\n" +
                "    {\n" +
                "        \"departureAirport\": {\"id\": 20},\n" +
                "        \"arrivalAirport\": {\"id\": 24},\n" +
                "        \"departureDateTime\": \"2023-09-03T11:30:00\",\n" +
                "        \"arrivalDateTime\": \"2023-09-03T13:45:00\",\n" +
                "        \"price\": 290.0\n" +
                "    },\n" +
                "    {\n" +
                "        \"departureAirport\": {\"id\": 24},\n" +
                "        \"arrivalAirport\": {\"id\": 20},\n" +
                "        \"departureDateTime\": \"2023-09-03T14:15:00\",\n" +
                "        \"arrivalDateTime\": \"2023-09-03T16:30:00\",\n" +
                "        \"price\": 290.0\n" +
                "    },\n" +
                "    {\n" +
                "        \"departureAirport\": {\"id\": 21},\n" +
                "        \"arrivalAirport\": {\"id\": 25},\n" +
                "        \"departureDateTime\": \"2023-09-04T13:00:00\",\n" +
                "        \"arrivalDateTime\": \"2023-09-04T15:15:00\",\n" +
                "        \"price\": 550.0\n" +
                "    },\n" +
                "    {\n" +
                "        \"departureAirport\": {\"id\": 25},\n" +
                "        \"arrivalAirport\": {\"id\": 21},\n" +
                "        \"departureDateTime\": \"2023-09-04T15:45:00\",\n" +
                "        \"arrivalDateTime\": \"2023-09-04T18:00:00\",\n" +
                "        \"price\": 550.0\n" +
                "    },\n" +
                "    {\n" +
                "        \"departureAirport\": {\"id\": 22},\n" +
                "        \"arrivalAirport\": {\"id\": 26},\n" +
                "        \"departureDateTime\": \"2023-09-05T10:45:00\",\n" +
                "        \"arrivalDateTime\": \"2023-09-05T13:00:00\",\n" +
                "        \"price\": 410.0\n" +
                "    },\n" +
                "    {\n" +
                "        \"departureAirport\": {\"id\": 26},\n" +
                "        \"arrivalAirport\": {\"id\": 22},\n" +
                "        \"departureDateTime\": \"2023-09-05T13:30:00\",\n" +
                "        \"arrivalDateTime\": \"2023-09-05T15:45:00\",\n" +
                "        \"price\": 410.0\n" +
                "    },\n" +
                "    {\n" +
                "        \"departureAirport\": {\"id\": 23},\n" +
                "        \"arrivalAirport\": {\"id\": 27},\n" +
                "        \"departureDateTime\": \"2023-09-06T14:15:00\",\n" +
                "        \"arrivalDateTime\": \"2023-09-06T16:30:00\",\n" +
                "        \"price\": 320.0\n" +
                "    },\n" +
                "    {\n" +
                "        \"departureAirport\": {\"id\": 27},\n" +
                "        \"arrivalAirport\": {\"id\": 23},\n" +
                "        \"departureDateTime\": \"2023-09-06T17:00:00\",\n" +
                "        \"arrivalDateTime\": \"2023-09-06T19:15:00\",\n" +
                "        \"price\": 320.0\n" +
                "    },\n" +
                "    {\n" +
                "        \"departureAirport\": {\"id\": 24},\n" +
                "        \"arrivalAirport\": {\"id\": 28},\n" +
                "        \"departureDateTime\": \"2023-09-07T13:45:00\",\n" +
                "        \"arrivalDateTime\": \"2023-09-07T16:00:00\",\n" +
                "        \"price\": 490.0\n" +
                "    },\n" +
                "    {\n" +
                "        \"departureAirport\": {\"id\": 28},\n" +
                "        \"arrivalAirport\": {\"id\": 24},\n" +
                "        \"departureDateTime\": \"2023-09-07T16:30:00\",\n" +
                "        \"arrivalDateTime\": \"2023-09-07T18:45:00\",\n" +
                "        \"price\": 490.0\n" +
                "    },\n" +
                "    {\n" +
                "        \"departureAirport\": {\"id\": 25},\n" +
                "        \"arrivalAirport\": {\"id\": 29},\n" +
                "        \"departureDateTime\": \"2023-09-08T10:30:00\",\n" +
                "        \"arrivalDateTime\": \"2023-09-08T12:45:00\",\n" +
                "        \"price\": 370.0\n" +
                "    },\n" +
                "    {\n" +
                "        \"departureAirport\": {\"id\": 29},\n" +
                "        \"arrivalAirport\": {\"id\": 25},\n" +
                "        \"departureDateTime\": \"2023-09-08T13:15:00\",\n" +
                "        \"arrivalDateTime\": \"2023-09-08T15:30:00\",\n" +
                "        \"price\": 370.0\n" +
                "    },\n" +
                "    {\n" +
                "        \"departureAirport\": {\"id\": 26},\n" +
                "        \"arrivalAirport\": {\"id\": 30},\n" +
                "        \"departureDateTime\": \"2023-09-09T14:45:00\",\n" +
                "        \"arrivalDateTime\": \"2023-09-09T17:00:00\",\n" +
                "        \"price\": 290.0\n" +
                "    },\n" +
                "    {\n" +
                "        \"departureAirport\": {\"id\": 30},\n" +
                "        \"arrivalAirport\": {\"id\": 26},\n" +
                "        \"departureDateTime\": \"2023-09-09T17:30:00\",\n" +
                "        \"arrivalDateTime\": \"2023-09-09T19:45:00\",\n" +
                "        \"price\": 290.0\n" +
                "    }\n" +
                "]";

        try {
            JSONArray jsonArray = new JSONArray(jsonData);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject flightJson = jsonArray.getJSONObject(i);

                JSONObject departureAirportJson = flightJson.getJSONObject("departureAirport");
                long departureAirportId = departureAirportJson.getLong("id");
                Airport departureAirport = airportRepository.findById(departureAirportId).orElse(null);

                JSONObject arrivalAirportJson = flightJson.getJSONObject("arrivalAirport");
                long arrivalAirportId = arrivalAirportJson.getLong("id");
                Airport arrivalAirport = airportRepository.findById(arrivalAirportId).orElse(null);

                String departureDateTimeString = flightJson.getString("departureDateTime");
                LocalDateTime departureDateTime = LocalDateTime.parse(departureDateTimeString, DateTimeFormatter.ISO_DATE_TIME);

                String arrivalDateTimeString = flightJson.getString("arrivalDateTime");
                LocalDateTime arrivalDateTime = LocalDateTime.parse(arrivalDateTimeString, DateTimeFormatter.ISO_DATE_TIME);

                double price = flightJson.getDouble("price");

                Flight flight = new Flight(departureAirport, arrivalAirport, departureDateTime, arrivalDateTime, price);
                flightRepository.save(flight);
            }

            System.out.println("Flight data loaded.");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
