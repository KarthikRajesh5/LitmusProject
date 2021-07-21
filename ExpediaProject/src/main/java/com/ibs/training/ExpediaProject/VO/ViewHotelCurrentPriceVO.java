package com.ibs.training.ExpediaProject.VO;

public class ViewHotelCurrentPriceVO {
    private String formatted;

    public String getFormatted() {
        return formatted;
    }

    public void setFormatted(String formatted) {
        this.formatted = formatted;
    }

    @Override
    public String toString() {
        return "ViewHotelCurrentPriceVO{" +
                "formatted='" + formatted + '\'' +
                '}';
    }
}
