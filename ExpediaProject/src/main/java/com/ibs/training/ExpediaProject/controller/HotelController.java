package com.ibs.training.ExpediaProject.controller;

import com.ibs.training.ExpediaProject.VO.ResultsVO;
import com.ibs.training.ExpediaProject.dto.HotelDTO;
import com.ibs.training.ExpediaProject.entity.HotelBookingEntity;
import com.ibs.training.ExpediaProject.service.HotelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping("/hotels")
public class HotelController {

    private HotelServiceImpl hotelServiceImplementation;

    private boolean booking=false;

    @Autowired
    public HotelController(HotelServiceImpl hotelSearchService){
        this.hotelServiceImplementation =hotelSearchService;
    }

    //Hotel Search
    @RequestMapping("/search")
    public String searchResults(@RequestParam(defaultValue ="default") String location,
                                @RequestParam(required = false) String checkin,
                                @RequestParam(required = false) String checkout,
                                @RequestParam(required = false) String travellers,
                                @RequestParam(required = false) String rooms,
                                Model model){

        List<ResultsVO> searchResult = null;
        if(!location.equals("default")) {
            searchResult = hotelServiceImplementation.HotelSearch(location,checkin,checkout,travellers,rooms);
            if (searchResult != null) {
                model.addAttribute("searchResults", searchResult);
                List<String> imageList=hotelServiceImplementation.getHotelImages();
                model.addAttribute("images",imageList);
            }
            else {
                model.addAttribute("error_message",true);
            }
        }
        else{
            model.addAttribute("searchResults", searchResult);
        }
        return "hotelresults";
    }

    //View Hotel Details
    @RequestMapping("/details")
    public String viewHotel(@RequestParam(required = false) String id,
                            @RequestParam(required = false)  String hotelName,
                            @RequestParam(required = false)  String starRating,
                            @RequestParam(required = false)  String address,
                            @RequestParam(required=false) String image,
                            Model model) {

        HotelDTO hotelDetails=hotelServiceImplementation.viewHotel(id,hotelName,starRating,address);
        model.addAttribute("hotelDetails",hotelDetails);
        model.addAttribute("hotelimage",image);
        System.out.println(hotelDetails.toString());
        return "hoteldetails";
    }

    //Booking
    @PostMapping("/booking")
    public String hotelBooking(@RequestParam(required=false) String image,Model model){

        HotelDTO hotelDetails=hotelServiceImplementation.hotelBooking();

        model.addAttribute("booking",true);
        model.addAttribute("hotelDetails",hotelDetails);
        model.addAttribute("hotelimage",image);
        booking=true;

        return "hoteldetails";
    }

    //itinerary
    @RequestMapping("/itinerary")
    public String hotelItinerary(@RequestParam(defaultValue ="default") String bookingId){
        bookingId="bookingId-1244746321178251";
        if(bookingId=="default"){

        }
        else{
            HotelBookingEntity entity=hotelServiceImplementation.getItinerary(bookingId);
            System.out.println(entity.getHotelId());
        }
        return "home";
    }
}
