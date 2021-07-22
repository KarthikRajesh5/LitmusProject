package com.ibs.training.ExpediaProject.controller;

import com.ibs.training.ExpediaProject.VO.FlightsVO;
import com.ibs.training.ExpediaProject.service.FlightSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/flights")
public class FlightSearchController {

    private FlightSearchService flightSearchService;

    @Autowired
    public FlightSearchController(FlightSearchService flightSearchService) {
        this.flightSearchService = flightSearchService;
    }


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
}