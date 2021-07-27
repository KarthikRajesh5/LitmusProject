package com.ibs.training.ExpediaProject.VO;

import java.util.List;

public class FlightResponseVO {

    List<FlightsVO> flights;

    public List<FlightsVO> getFlights() {
        return flights;
    }

    public void setFlights(List<FlightsVO> flights) {
        this.flights = flights;
    }

    @Override
    public String toString() {
        return "FlightResponseVO{" +
                "flights=" + flights +
                '}';
    }
}

