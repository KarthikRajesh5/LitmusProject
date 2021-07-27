package com.ibs.training.ExpediaProject.VO;

import java.util.List;

public class ViewHotelOverviewSectionsVO {
    private String title;
    private String type;
    List<String> content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ViewHotelOverviewSectionsVO{" +
                "title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", content=" + content +
                '}';
    }
}
