package com.ibs.training.ExpediaProject.controller;

import com.ibs.training.ExpediaProject.VO.ResultsVO;
import com.ibs.training.ExpediaProject.service.HotelSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/hotels")
public class HotelSearchController {

    HotelSearchService hotelSearchService;

    @Autowired
    public HotelSearchController(HotelSearchService hotelSearchService){
        this.hotelSearchService=hotelSearchService;
    }

    @RequestMapping("/search")
    public String searchResults(@RequestParam(defaultValue ="default") String location,Model model){
        List<ResultsVO> searchResult = null;
        if(!location.equals("default")) {
            searchResult = hotelSearchService.HotelSearch(location);
            model.addAttribute("searchResults", searchResult);
        }
        else{
            model.addAttribute("searchResults", null);
        }
        return "results";
    }

    @PostMapping("/view")
    public String viewHotel(@RequestParam(required = false) String id){
        //get address , hotel name , StarRating as request parameter
        id="454945";
        hotelSearchService.viewHotel(id);
        return "viewHotel";
    }

}
