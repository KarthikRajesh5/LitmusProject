package com.ibs.training.ExpediaProject.controller;

import com.ibs.training.ExpediaProject.VO.ResultsVO;
import com.ibs.training.ExpediaProject.service.HotelSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/hotels")
public class HotelSearchController {

    HotelSearchService hotelSearchService;

    @Autowired
    public HotelSearchController(HotelSearchService hotelSearchService){
        this.hotelSearchService=hotelSearchService;
    }
    @GetMapping("/search")
    public String searchPage(){
        //return search page
        return "Search page";
    }

    @PostMapping("/search")
    public ModelAndView searchResults(@RequestParam String location){
        List<ResultsVO> searchResult=hotelSearchService.HotelSearch(location);
        ModelAndView model=new ModelAndView("HotelSearchResults");
        model.addObject(searchResult);
        return model;
    }

}
