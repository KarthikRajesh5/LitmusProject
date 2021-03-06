package com.ibs.training.ExpediaProject.controller;

import com.ibs.training.ExpediaProject.VO.FlightsVO;
import com.ibs.training.ExpediaProject.dto.FlightDTO;
import com.ibs.training.ExpediaProject.dto.PassengerDTO;
import com.ibs.training.ExpediaProject.service.FlightSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/flights")
public class FlightSearchController {

    private FlightSearchService flightSearchService;

    private static final Logger LOGGER= LoggerFactory.getLogger(FlightSearchController.class);

    @Autowired
    public FlightSearchController(FlightSearchService flightSearchService) {
        this.flightSearchService = flightSearchService;
    }

    /*---------Flight Search---------- */

    @RequestMapping("/search")
    public String searchFlight(@RequestParam(defaultValue = "default") String departure,
                               @RequestParam(defaultValue = "default") String arrival,
                               @RequestParam(defaultValue = "default") String departureDate,
                               Model model) {

        List<FlightsVO> searchFlightResult;

        if (!departure.equals("default") && (!arrival.equals("default") && (!departureDate.equals("default")))) {
            searchFlightResult = flightSearchService.flightSearch(departure, arrival, departureDate);
            if (searchFlightResult.isEmpty()) {
                model.addAttribute("notFound", "No Flights Available!");
            } else {
                model.addAttribute("searchFlightResults", searchFlightResult);
                List<String> imageList=new ArrayList<>();
                imageList.add("https://limusflightimagesbucket.s3.us-east-2.amazonaws.com/Airline.jpg");
                imageList.add("https://limusflightimagesbucket.s3.us-east-2.amazonaws.com/Airline7.jpg");
                imageList.add("https://limusflightimagesbucket.s3.us-east-2.amazonaws.com/Airline3.jpg");
                imageList.add("https://limusflightimagesbucket.s3.us-east-2.amazonaws.com/Airline4.jpg");
                imageList.add("https://limusflightimagesbucket.s3.us-east-2.amazonaws.com/Airline5.jpg");
                imageList.add("https://limusflightimagesbucket.s3.us-east-2.amazonaws.com/Airline6.jpg");
                imageList.add("https://limusflightimagesbucket.s3.us-east-2.amazonaws.com/Airlines2.jpg");
                model.addAttribute("images",imageList);
            }
        } else{
            model.addAttribute("searchFlightResults", null);
        }
        return "flightSearchResults";
    }

    /*-----------Flight Results------------ */

    @RequestMapping("/view")
    public String viewFlight(@RequestParam String airlines,
                             @RequestParam String departure1,
                             @RequestParam String arrival1,
                             @RequestParam String departureDate1,
                             @RequestParam String departureTime,
                             @RequestParam String arrivalDate,
                             @RequestParam String arrivalTime,
                             @RequestParam String tripDuration,
                             @RequestParam String cabinClass,
                             @RequestParam Double price,
                             Model model) {

        //DateTimeFormatter df = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        //LocalDate depDate=LocalDate.parse(departureDate1,DateTimeFormatter.BASIC_ISO_DATE);
        //LocalDate arrDate=LocalDate.parse(arrivalDate,DateTimeFormatter.BASIC_ISO_DATE);

        LOGGER.info("From:" + departure1 +" TO:" + arrival1 + "Departure Date: " + departureDate1);
        FlightDTO flightDetails = flightSearchService.viewFlight(airlines,departure1,
                arrival1,departureDate1,
                departureTime,arrivalDate,
                arrivalTime,tripDuration,
                cabinClass,price);
        model.addAttribute("flightDetails",flightDetails);
        LOGGER.info("Flights available are"+flightDetails.toString());
        return "viewPage";
    }

    /*----------Flight Booking-----------*/

    @RequestMapping("/bookFlights")
    public String bookFlight(@RequestParam String airline,
                             @RequestParam String origin,
                             @RequestParam String destination,
                             @RequestParam String departDate,
                             @RequestParam String departTime,
                             @RequestParam String arrDate,
                             @RequestParam String arrTime,
                             @RequestParam String tripLength,
                             @RequestParam String type,
                             @RequestParam Double amount,
                             Model model) {

        LOGGER.info("From:" + origin +" TO:" + destination + "Departure Date: " + departDate);
        FlightDTO flightBook = flightSearchService.viewFlight(airline,origin,
                destination,departDate,
                departTime,arrDate,
                arrTime,tripLength,
                type,amount);
        model.addAttribute("flightBook",flightBook);
        model.addAttribute("passenger", new PassengerDTO());
        LOGGER.info("Flights found are"+flightBook.toString());
        flightSearchService.save(flightBook);
        return "bookFlights";
    }

    /*---------Passenger Details----------*/

    @PostMapping("/bookFlights")
    public String results(@ModelAttribute("passenger") PassengerDTO passengerDTO, Model model) {

        flightSearchService.set(passengerDTO);
        model.addAttribute("flightBook",new FlightDTO());
        return "bookDetails";
    }

}

