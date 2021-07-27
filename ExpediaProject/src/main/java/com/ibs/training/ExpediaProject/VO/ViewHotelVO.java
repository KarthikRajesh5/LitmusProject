package com.ibs.training.ExpediaProject.VO;

public class ViewHotelVO{
    private ViewHotelDataVO data;

    public ViewHotelDataVO getData() {
        return data;
    }

    public void setData(ViewHotelDataVO data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ViewHotelVO{" +
                "data=" + data +
                '}';
    }
}
