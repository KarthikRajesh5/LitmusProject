package com.ibs.training.ExpediaProject.VO;

public class PropertyDescriptionVO {
    ViewHotelFeaturedPriceVO featuredPrice;

    public ViewHotelFeaturedPriceVO getFeaturedPrice() {
        return featuredPrice;
    }

    public void setFeaturedPrice(ViewHotelFeaturedPriceVO featuredPrice) {
        this.featuredPrice = featuredPrice;
    }

    @Override
    public String toString() {
        return "PropertyDescriptionVO{" +
                "featuredPrice=" + featuredPrice +
                '}';
    }
}
