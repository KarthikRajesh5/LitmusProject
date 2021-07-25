package com.ibs.training.ExpediaProject.service;

import com.ibs.training.ExpediaProject.VO.FlightResponseVO;
import com.ibs.training.ExpediaProject.VO.FlightsVO;
import com.ibs.training.ExpediaProject.dto.FlightDTO;
import com.ibs.training.ExpediaProject.dto.PassengerDTO;
import com.ibs.training.ExpediaProject.entity.FlightEntity;
import com.ibs.training.ExpediaProject.entity.Passenger;
import com.ibs.training.ExpediaProject.repository.FlightRepository;
import com.ibs.training.ExpediaProject.repository.PassengerRepository;
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
import java.util.stream.Collectors;


@Service
public class FlightSearchService {

    /* Rapid API Key */
    @Value("${RapidAPI.Key}")
    private String key;

    /* Rapid API Host */
    @Value("${RapidAPI.host}")
    private String host;

    private FlightRepository flightRepository;

    private PassengerRepository passengerRepository;

    private final RestTemplate restTemplate;

    private FlightDTO flightDTO;

    private PassengerDTO passengerDTO;

    @Autowired
    public FlightSearchService(FlightRepository flightRepository, PassengerRepository passengerRepository, RestTemplate restTemplate, FlightDTO flightDTO, PassengerDTO passengerDTO) {
        this.flightRepository = flightRepository;
        this.passengerRepository = passengerRepository;
        this.restTemplate = restTemplate;
        this.flightDTO = flightDTO;
        this.passengerDTO = passengerDTO;
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
            flightSearchResults = flightResponse.getBody().getFlights().stream().limit(7).collect(Collectors.toList());
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

    /*setting the data in flightDTO*/

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

    /*----------------------------Flight Booking-------------------------------*/

    /*Saving the Flight Details to DB*/
    /*DTO data passed to FlightEntity*/

    public FlightEntity save(FlightDTO flightDTO) {

        FlightEntity flightEntity = new FlightEntity(flightDTO.getDepartureAirportCode(),
                flightDTO.getArrivalAirportCode(),
                flightDTO.getPrice(),
                flightDTO.getTripDuration(),
                flightDTO.getDepartureDate(),
                flightDTO.getDepartureTime(),
                flightDTO.getArrivalTime(),
                flightDTO.getCabinClass(),
                flightDTO.getAirlines(),
                flightDTO.getArrivalDate());

        return flightRepository.save(flightEntity);
    }

    /*-------------------------Passenger Details------------------------------------*/

    /*Saving Passenger Details to DB via Passenger Entity class*/
    /*DTO data passed to PassengerEntity*/

    public Passenger set(PassengerDTO passengerDTO) {
        Passenger passenger = new Passenger(passengerDTO.getFirstName(),
                passengerDTO.getLastName(),
                passengerDTO.getPassportNumber(),
                passengerDTO.getEmail(),
                passengerDTO.getAddress(),
                passengerDTO.getNoOfTravellers(),
                passengerDTO.getNoOfAdults(),
                passengerDTO.getNoOfInfants());
        return passengerRepository.save(passenger);
    }

}