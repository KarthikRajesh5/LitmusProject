package com.ibs.training.ExpediaProject.controller;

import com.ibs.training.ExpediaProject.VO.ResultsVO;
import com.ibs.training.ExpediaProject.service.HotelSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

//    @GetMapping("/search")
//    public String searchPage(){
//        //return search page
//        return "Search page";
//    }

    @RequestMapping("/search")
    public String searchResults(@RequestParam(defaultValue ="default") String location,Model model){
        if(!location.equals("default")) {
            List<ResultsVO> searchResult = hotelSearchService.HotelSearch(location);
            model.addAttribute("searchResults", searchResult);
        }
        else{
            model.addAttribute("searchResults",new ArrayList() );
        }
        return "results";
    }


}
