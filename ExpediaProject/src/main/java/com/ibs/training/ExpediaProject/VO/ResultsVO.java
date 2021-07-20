package com.ibs.training.ExpediaProject.VO;

public class ResultsVO {
    private String name;
    private Double starRating;
    private AddressVO address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getStarRating() {
        return starRating;
    }

    public void setStarRating(Double starRating) {
        this.starRating = starRating;
    }

    public AddressVO getAddress() {
        return address;
    }

    public void setAddress(AddressVO addressVO) {
        this.address = addressVO;
    }

    @Override
    public String toString() {
        return "Results{" +
                "name='" + name + '\'' +
                ", starRating=" + starRating +
                ", address=" + address +
                '}';
    }
}
