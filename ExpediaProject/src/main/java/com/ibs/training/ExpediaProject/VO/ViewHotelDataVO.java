package com.ibs.training.ExpediaProject.VO;

public class ViewHotelDataVO {
    private ViewHotelBodyVO body;

    public ViewHotelBodyVO getBody() {
        return body;
    }

    public void setBody(ViewHotelBodyVO body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "ViewHotelDataVO{" +
                "body=" + body +
                '}';
    }
}
