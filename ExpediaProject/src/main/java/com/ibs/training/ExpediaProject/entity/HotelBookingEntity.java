package com.ibs.training.ExpediaProject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name="HotelBooking")
public class HotelBookingEntity {

    @Id
    @Column(name="username")
    private String username;

    @Column(name="hotelId")
    private String hotelId;

    @Column(name="checkin")
    private LocalDate checkin;

    @Column(name="checkout")
    private LocalDate checkout;

    @Column(name="travellers")
    private int travellers;

    @Column(name="roomrent")
    private double roomrent;

    @Column(name="duration")
    private int duration;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
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

    public double getRoomrent() {
        return roomrent;
    }

    public void setRoomrent(double roomrent) {
        this.roomrent = roomrent;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
