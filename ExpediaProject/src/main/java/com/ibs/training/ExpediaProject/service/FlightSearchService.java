package com.ibs.training.ExpediaProject.service;

import com.ibs.training.ExpediaProject.VO.FlightResponseVO;
import com.ibs.training.ExpediaProject.VO.FlightsVO;
import com.ibs.training.ExpediaProject.dto.FlightDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;


@Service
public class FlightSearchService {

    /* Rapid API Key */
    @Value("${RapidAPI.Key}")
    private String key;

    /* Rapid API Host */
    @Value("${RapidAPI.host}")
    private String host;

    private final RestTemplate restTemplate;

    private FlightDTO flightDTO;

    @Autowired
    public FlightSearchService(RestTemplate restTemplate, FlightDTO flightDTO) {
        this.restTemplate = restTemplate;
        this.flightDTO = flightDTO;
    }

    /*------------------------------------Flight Search--------------------------------------*/

    public List<FlightsVO> flightSearch(String departureAirport, String arrivalAirport, String departureDate) {

        /*Setting Headers*/
        org.springframework.http.HttpHeaders header = new HttpHeaders();
        header.set("Content-Type", "application/json");
        header.set("Accept", "application/json");
        header.set("X-RapidAPI-Key", key);
        header.set("X-RapidAPI-Host", host);
        HttpEntity<String> entity = new HttpEntity<>(header);

        /*API : Google Flights API*/
        /*Passing the API URL and setting the querries*/

        final String url = "https://google-flights-search.p.rapidapi.com/search";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("departure_airport_code", departureAirport)
                .queryParam("arrival_airport_code", arrivalAirport)
                .queryParam("departure_date", departureDate);

        ResponseEntity<FlightResponseVO> flightResponse = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                FlightResponseVO.class
        );

        /*Showcasing the Flight Results from the API*/
        List<FlightsVO> flightSearchResults;
        try {
            flightSearchResults = flightResponse.getBody().getFlights();
            flightSearchResults.forEach(e -> System.out.println("Flight Name : " + e.getAirlines()
                    + " Departure Airport : " + e.getDeparture_airport_code()
                    + " Arrival Airport " + e.getArrival_airport_code()
                    + " Departure Date :" + e.getDeparture_date()
                    + " Departure Time :" + e.getDeparture_time()
                    + " Arrival Date :" + e.getArrival_date()
                    + " Arrival Time :" + e.getArrival_time()
                    + " Trip Duration :" + e.getTrip_duration()
                    + " Cabin Class :" + e.getCabin_class()
                    + " Price :" + e.getPrice()));

        } catch (NullPointerException e) {
            return null;
        }

        return flightSearchResults;
    }

    /*---------------------------Flight Details----------------------------------*/

    public FlightDTO viewFlight(String airlines, String departure, String arrival, String departureDate, String departureTime,
                                String arrivalDate, String arrivalTime, String tripDuration, String cabinClass, Double price) {

        flightDTO.setAirlines(airlines);
        flightDTO.setDepartureAirportCode(departure);
        flightDTO.setArrivalAirportCode(arrival);
        flightDTO.setDepartureDate(departureDate);
        flightDTO.setDepartureTime(departureTime);
        flightDTO.setArrivalDate(arrivalDate);
        flightDTO.setArrivalTime(arrivalTime);
        flightDTO.setTripDuration(tripDuration);
        flightDTO.setCabinClass(cabinClass);
        flightDTO.setPrice(price);
        return flightDTO;
    }

}