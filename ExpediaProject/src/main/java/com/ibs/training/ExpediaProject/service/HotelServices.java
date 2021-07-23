package com.ibs.training.ExpediaProject.service;

import com.ibs.training.ExpediaProject.VO.ResultsVO;
import com.ibs.training.ExpediaProject.dto.HotelDTO;

import java.util.List;

public interface HotelServices {

    public List<ResultsVO> HotelSearch(String searchLocation,String checkin,String checkout,String travellers);

    public HotelDTO viewHotel(String id, String hotelName, String starRating, String address);

}
