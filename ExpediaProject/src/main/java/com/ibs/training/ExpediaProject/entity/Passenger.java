package com.ibs.training.ExpediaProject.entity;

import javax.persistence.*;

@Entity
@Table
public class Passenger {

    @Id
    @Column(name = "PASSENGERID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long passengerId;

    @Column(name = "FIRSTNAME")
    private String firstName;
    @Column(name = "LASTNAME")
    private String lastName;
    @Column(name = "PASSPORTNUMBER")
    private String passportNumber;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "TRAVELLERS")
    private int noOfTravellers;
    @Column(name = "ADULTS")
    private int noOfAdults;
    @Column(name = "INFANTS")
    private int noOfInfants;

    public Passenger() {
    }

    public Passenger(String firstName, String lastName, String passportNumber, String email, String address, int noOfTravellers, int noOfAdults, int noOfInfants) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportNumber = passportNumber;
        this.email = email;
        this.address = address;
        this.noOfTravellers = noOfTravellers;
        this.noOfAdults = noOfAdults;
        this.noOfInfants = noOfInfants;
    }

    public Long getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Long passengerId) {
        this.passengerId = passengerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNoOfTravellers() {
        return noOfTravellers;
    }

    public void setNoOfTravellers(int noOfTravellers) {
        this.noOfTravellers = noOfTravellers;
    }

    public int getNoOfAdults() {
        return noOfAdults;
    }

    public void setNoOfAdults(int noOfAdults) {
        this.noOfAdults = noOfAdults;
    }

    public int getNoOfInfants() {
        return noOfInfants;
    }

    public void setNoOfInfants(int noOfInfants) {
        this.noOfInfants = noOfInfants;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "passengerId=" + passengerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", noOfTravellers=" + noOfTravellers +
                ", noOfAdults=" + noOfAdults +
                ", noOfInfants=" + noOfInfants +
                '}';
    }
}
