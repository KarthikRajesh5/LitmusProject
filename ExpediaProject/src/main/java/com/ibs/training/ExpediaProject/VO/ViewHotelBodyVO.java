package com.ibs.training.ExpediaProject.VO;

public class ViewHotelBodyVO {
    private ViewHotelOverviewVO overview;
    private PropertyDescriptionVO propertyDescription;

    public ViewHotelOverviewVO getOverview() {
        return overview;
    }

    public void setOverview(ViewHotelOverviewVO overview) {
        this.overview = overview;
    }

    public PropertyDescriptionVO getPropertyDescription() {
        return propertyDescription;
    }

    public void setPropertyDescription(PropertyDescriptionVO propertyDescription) {
        this.propertyDescription = propertyDescription;
    }

    @Override
    public String toString() {
        return "ViewHotelBodyVO{" +
                "overview=" + overview +
                ", propertyDescription=" + propertyDescription +
                '}';
    }
}
