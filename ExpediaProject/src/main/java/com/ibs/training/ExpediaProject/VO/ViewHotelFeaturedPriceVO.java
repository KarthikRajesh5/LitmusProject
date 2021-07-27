package com.ibs.training.ExpediaProject.VO;

public class ViewHotelFeaturedPriceVO {
    ViewHotelCurrentPriceVO currentPrice;

    public ViewHotelCurrentPriceVO getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(ViewHotelCurrentPriceVO currentPrice) {
        this.currentPrice = currentPrice;
    }

    @Override
    public String toString() {
        return "ViewHotelFeaturedPriceVO{" +
                "currentPrice=" + currentPrice +
                '}';
    }
}
