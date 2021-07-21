package com.ibs.training.ExpediaProject.VO;

import java.util.List;

public class ViewHotelOverviewVO {

    List<ViewHotelOverviewSectionsVO> overviewSections;

    public List<ViewHotelOverviewSectionsVO> getOverviewSections() {
        return overviewSections;
    }

    public void setOverviewSections(List<ViewHotelOverviewSectionsVO> overviewSections) {
        this.overviewSections = overviewSections;
    }

    @Override
    public String toString() {
        return "ViewHotelOverviewVO{" +
                "overviewSections=" + overviewSections +
                '}';
    }
}
