package com.ibs.training.ExpediaProject.controller;

import com.ibs.training.ExpediaProject.VO.ResultsVO;
import com.ibs.training.ExpediaProject.dto.HotelDTO;
import com.ibs.training.ExpediaProject.service.HotelServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/hotels")
public class HotelController {

    private HotelServiceImplementation hotelServiceImplementation;

    private boolean booking=false;

    @Autowired
    public HotelController(HotelServiceImplementation hotelSearchService){
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

        System.out.println(location+" "+checkin+" "+checkout+" "+travellers);

        LocalDate currentDate=LocalDate.now();
        model.addAttribute("currentDate",currentDate);
        System.out.println(currentDate);
        List<ResultsVO> searchResult = null;
        if(!location.equals("default")) {
            searchResult = hotelServiceImplementation.HotelSearch(location,checkin,checkout,travellers,rooms);
            if (searchResult != null) {
                model.addAttribute("searchResults", searchResult);
                List<String> imageList=new ArrayList<>();
                imageList.add("https://litmus-hotel-images-bucket.s3.us-east-2.amazonaws.com/hotel1.jpg");
                imageList.add("https://litmus-hotel-images-bucket.s3.us-east-2.amazonaws.com/hotel2.jpg");
                imageList.add("https://litmus-hotel-images-bucket.s3.us-east-2.amazonaws.com/hotel3.jfif");
                imageList.add("https://litmus-hotel-images-bucket.s3.us-east-2.amazonaws.com/hotel4.webp");
                imageList.add("https://litmus-hotel-images-bucket.s3.us-east-2.amazonaws.com/hotel5.jpg");
                imageList.add("https://litmus-hotel-images-bucket.s3.us-east-2.amazonaws.com/hotel6.jpg");
                imageList.add("https://litmus-hotel-images-bucket.s3.us-east-2.amazonaws.com/hotel7.webp");
                imageList.add("https://litmus-hotel-images-bucket.s3.us-east-2.amazonaws.com/hotel8.jpg");
                imageList.add("https://litmus-hotel-images-bucket.s3.us-east-2.amazonaws.com/hotel9.webp");
                imageList.add("https://litmus-hotel-images-bucket.s3.us-east-2.amazonaws.com/hotel10.jpg");
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
        //get address , hotel name , StarRating, image url as request parameter
        System.out.println(id +" "+hotelName+" "+starRating+" "+address);
//        if(booking==true){
//            System.out.println("Booking is true");
//            return "hoteldetails";
//        }
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

}
