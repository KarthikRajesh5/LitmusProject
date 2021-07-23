package com.ibs.training.ExpediaProject.dto;

import java.time.LocalDate;
import java.util.List;

public class HotelDTO {

    //include in bookingDTO
    //private String username;

    private String location;

    private LocalDate checkin;//

    private LocalDate checkout;//

    private int travellers;//

    private String hotelId; //pkey

    private String hotelName;

    private double starRating;

    private String address;

    private List<String> propertyAmenities;

    private List<String> roomAmenities;

    private List<String> locationSection;

    private double price;


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getCheckin() {
        return checkin;
    }

    public void setCheckin(LocalDate checkin) {
        this.checkin = checkin;
    }

    public LocalDate getCheckout() {
        return checkout;
    }

    public void setCheckout(LocalDate checkout) {
        this.checkout = checkout;
    }

    public int getTravellers() {
        return travellers;
    }

    public void setTravellers(int travellers) {
        this.travellers = travellers;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public double getStarRating() {
        return starRating;
    }

    public void setStarRating(double starRating) {
        this.starRating = starRating;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getPropertyAmenities() {
        return propertyAmenities;
    }

    public void setPropertyAmenities(List<String> propertyAmenities) {
        this.propertyAmenities = propertyAmenities;
    }

    public List<String> getRoomAmenities() {
        return roomAmenities;
    }

    public void setRoomAmenities(List<String> roomAmenities) {
        this.roomAmenities = roomAmenities;
    }

    public List<String> getLocationSection() {
        return locationSection;
    }

    public void setLocationSection(List<String> locationSection) {
        this.locationSection = locationSection;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "HotelDTO{" +
                ", location='" + location + '\'' +
                ", checkin=" + checkin +
                ", checkout=" + checkout +
                ", travellers=" + travellers +
                ", hotelId='" + hotelId + '\'' +
                ", hotelName='" + hotelName + '\'' +
                ", starRating=" + starRating +
                ", address='" + address + '\'' +
                ", propertyAmenities=" + propertyAmenities +
                ", roomAmenities=" + roomAmenities +
                ", locationSection=" + locationSection +
                ", price=" + price +
                '}';
    }
}
