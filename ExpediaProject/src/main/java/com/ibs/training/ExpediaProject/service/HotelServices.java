package com.ibs.training.ExpediaProject.service;

import com.ibs.training.ExpediaProject.VO.ResultsVO;

import java.util.List;

public interface HotelServices {

    public List<ResultsVO> HotelSearch(String searchLocation);

    public void viewHotel(String id);

}
