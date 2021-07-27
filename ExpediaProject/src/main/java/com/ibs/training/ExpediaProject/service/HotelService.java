package com.ibs.training.ExpediaProject.service;

import com.ibs.training.ExpediaProject.VO.ResultsVO;
import com.ibs.training.ExpediaProject.dto.HotelDTO;
import com.ibs.training.ExpediaProject.entity.HotelBookingEntity;

import java.util.List;

public interface HotelService {

    public List<ResultsVO> HotelSearch(String searchLocation,String checkin,String checkout,String travellers,String rooms);

    public HotelDTO viewHotel(String id, String hotelName, String starRating, String address);

    public HotelDTO hotelBooking();

    public List<String> getHotelImages();

   public HotelBookingEntity getItinerary(String bookingId);
}
