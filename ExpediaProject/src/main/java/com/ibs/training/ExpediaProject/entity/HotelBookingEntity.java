package com.ibs.training.ExpediaProject.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class HotelBookingEntity {

    @Id
    private String username;

    private String hotelId;

    private LocalDate checkin;

    private LocalDate checkout;

    private int travellers;


}
