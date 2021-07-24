package com.ibs.training.ExpediaProject.entity;

import javax.persistence.*;

@Entity
@Table
public class FlightEntity {

    @Id
    @Column(name = "FLIGHTID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long flightId;

    @Column(name = "ORIGIN")
    private String departureAirportCode;
    @Column(name = "DESTINATION")
    private String arrivalAirportCode;
    @Column(name = "PRICE")
    private Double price;
    @Column(name = "TRIPDURATION")
    private String tripDuration;
    @Column(name = "DEPARTUREDATE")
    private String departureDate;
    @Column(name = "DEPARTURETIME")
    private String departureTime;
    @Column(name = "ARRIVALTIME")
    private String arrivalTime;
    @Column(name = "CABINCLASS")
    private String cabinClass;
    @Column(name = "AIRLINES")
    private String airlines;
    @Column(name = "ARRIVALDATE")
    private String arrivalDate;

    public FlightEntity() {
    }

    public FlightEntity(String departureAirportCode, String arrivalAirportCode, Double price, String tripDuration, String departureDate, String departureTime, String arrivalTime, String cabinClass, String airlines, String arrivalDate) {
        this.departureAirportCode = departureAirportCode;
        this.arrivalAirportCode = arrivalAirportCode;
        this.price = price;
        this.tripDuration = tripDuration;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.cabinClass = cabinClass;
        this.airlines = airlines;
        this.arrivalDate = arrivalDate;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public String getDepartureAirportCode() {
        return departureAirportCode;
    }

    public void setDepartureAirportCode(String departureAirportCode) {
        this.departureAirportCode = departureAirportCode;
    }

    public String getArrivalAirportCode() {
        return arrivalAirportCode;
    }

    public void setArrivalAirportCode(String arrivalAirportCode) {
        this.arrivalAirportCode = arrivalAirportCode;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTripDuration() {
        return tripDuration;
    }

    public void setTripDuration(String tripDuration) {
        this.tripDuration = tripDuration;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getCabinClass() {
        return cabinClass;
    }

    public void setCabinClass(String cabinClass) {
        this.cabinClass = cabinClass;
    }

    public String getAirlines() {
        return airlines;
    }

    public void setAirlines(String airlines) {
        this.airlines = airlines;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    @Override
    public String toString() {
        return "FlightEntity{" +
                "flightId=" + flightId +
                ", departureAirportCode='" + departureAirportCode + '\'' +
                ", arrivalAirportCode='" + arrivalAirportCode + '\'' +
                ", price=" + price +
                ", tripDuration='" + tripDuration + '\'' +
                ", departureDate='" + departureDate + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", arrivalTime='" + arrivalTime + '\'' +
                ", cabinClass='" + cabinClass + '\'' +
                ", airlines='" + airlines + '\'' +
                ", arrivalDate='" + arrivalDate + '\'' +
                '}';
    }
}
