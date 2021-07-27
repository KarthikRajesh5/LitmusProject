package com.ibs.training.ExpediaProject.VO;

public class EntitiesVO {

    private long destinationId;

    public long getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(long destinationId) {
        this.destinationId = destinationId;
    }

    @Override
    public String toString() {
        return "Entities{" +
                "destinationId=" + destinationId +
                '}';
    }
}
