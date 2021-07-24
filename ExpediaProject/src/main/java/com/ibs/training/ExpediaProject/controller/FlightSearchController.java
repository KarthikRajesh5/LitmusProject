package com.ibs.training.ExpediaProject.controller;

import com.ibs.training.ExpediaProject.VO.FlightsVO;
import com.ibs.training.ExpediaProject.dto.FlightDTO;
import com.ibs.training.ExpediaProject.service.FlightSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}

