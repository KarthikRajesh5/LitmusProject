package com.ibs.training.ExpediaProject.controller;

import com.ibs.training.ExpediaProject.VO.ResultsVO;
import com.ibs.training.ExpediaProject.dto.HotelBookingDTO;
import com.ibs.training.ExpediaProject.dto.HotelDTO;
import com.ibs.training.ExpediaProject.service.HotelServiceImplementation;
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

    private HotelServiceImplementation hotelServiceImplementation;


    @Autowired
    public HotelController(HotelServiceImplementation hotelSearchService){
        this.hotelServiceImplementation =hotelSearchService;

    }




    //Hotel Search
    @RequestMapping("/search")
    public String searchResults(@RequestParam(defaultValue ="default") String location,
                                @RequestParam(required=false) String checkin,
                                @RequestParam(required=false) String checkout,
                                @RequestParam(required=false) String travellers,
                                Model model){

        System.out.println(location+" "+checkin+" "+checkout+" "+travellers);

        List<ResultsVO> searchResult = null;
        if(!location.equals("default")) {
            searchResult = hotelServiceImplementation.HotelSearch(location,checkin,checkout,travellers);
            if (searchResult != null) {
                model.addAttribute("searchResults", searchResult);
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
    public String viewHotel(@RequestParam String id,
                            @RequestParam String hotelName,
                            @RequestParam String starRating,
                            @RequestParam String address,
                            Model model) {
        //get address , hotel name , StarRating, image url as request parameter
        System.out.println(id +" "+hotelName+" "+starRating+" "+address);
        HotelDTO hotelDetails=hotelServiceImplementation.viewHotel(id,hotelName,starRating,address);
        model.addAttribute("hotelDetails",hotelDetails);
        System.out.println(hotelDetails.toString());
        return "hoteldetails";
    }


    //Booking
    @PostMapping("/booking")
    public void hotelBooking(HotelBookingDTO hotelBookingDTO,Model model){
        /* ->incoming details stored to HotelBookingDTO
        *  ->find the logged in username
        *  and set it to the HotelBooking DTO(using Spring Security)
        *  ->save it to database
        *  ->add  "booking successfully" as message in model
        * ->redirect to ViewHotels page and display the message  */
    }

}
