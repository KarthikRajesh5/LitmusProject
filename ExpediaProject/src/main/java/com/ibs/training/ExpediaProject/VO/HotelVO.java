package com.ibs.training.ExpediaProject.VO;

public class HotelVO {

    private String result;
    private DataVO data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public DataVO getData() {
        return data;
    }

    public void setData(DataVO data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "result='" + result + '\'' +
                ", data=" + data +
                '}';
    }
}
