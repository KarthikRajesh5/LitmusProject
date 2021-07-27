package com.ibs.training.ExpediaProject.VO;

public class FlightsVO {

    private String departure_airport_code;
    private String arrival_airport_code;
    private Double price;
    private String trip_duration;
    private String departure_date;
    private String departure_time;
    private String arrival_time;
    private String cabin_class;
    private String airlines;
    private String arrival_date;

    public String getDeparture_airport_code() {
        return departure_airport_code;
    }

    public void setDeparture_airport_code(String departure_airport_code) {
        this.departure_airport_code = departure_airport_code;
    }

    public String getArrival_airport_code() {
        return arrival_airport_code;
    }

    public void setArrival_airport_code(String arrival_airport_code) {
        this.arrival_airport_code = arrival_airport_code;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTrip_duration() {
        return trip_duration;
    }

    public void setTrip_duration(String trip_duration) {
        this.trip_duration = trip_duration;
    }

    public String getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(String departure_date) {
        this.departure_date = departure_date;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(String departure_time) {
        this.departure_time = departure_time;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        this.arrival_time = arrival_time;
    }

    public String getCabin_class() {
        return cabin_class;
    }

    public void setCabin_class(String cabin_class) {
        this.cabin_class = cabin_class;
    }

    public String getAirlines() {
        return airlines;
    }

    public void setAirlines(String airlines) {
        this.airlines = airlines;
    }

    public String getArrival_date() {
        return arrival_date;
    }

    public void setArrival_date(String arrival_date) {
        this.arrival_date = arrival_date;
    }

    @Override
    public String toString() {
        return "FlightsVO{" +
                "departure_airport_code='" + departure_airport_code + '\'' +
                ", arrival_airport_code='" + arrival_airport_code + '\'' +
                ", price=" + price +
                ", trip_duration='" + trip_duration + '\'' +
                ", departure_date='" + departure_date + '\'' +
                ", departure_time='" + departure_time + '\'' +
                ", arrival_time='" + arrival_time + '\'' +
                ", cabin_class='" + cabin_class + '\'' +
                ", airlines='" + airlines + '\'' +
                ", arrival_date='" + arrival_date + '\'' +
                '}';
    }
}