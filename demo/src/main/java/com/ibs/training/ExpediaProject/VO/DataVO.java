package com.ibs.training.ExpediaProject.VO;

public class DataVO {
    private BodyVO body;

    public BodyVO getBody() {
        return body;
    }

    public void setBody(BodyVO bodyVO) {
        this.body = bodyVO;
    }

    @Override
    public String toString() {
        return "Data{" +
                "body=" + body +
                '}';
    }
}
