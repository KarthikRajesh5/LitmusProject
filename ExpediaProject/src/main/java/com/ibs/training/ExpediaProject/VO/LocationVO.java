package com.ibs.training.ExpediaProject.VO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationVO {
    private List<SuggestionVO> suggestions;
    String term;
    String moresuggestions;

    public List<SuggestionVO> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<SuggestionVO> suggestions) {
        this.suggestions = suggestions;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getMoresuggestions() {
        return moresuggestions;
    }

    public void setMoresuggestions(String moresuggestions) {
        this.moresuggestions = moresuggestions;
    }

    @Override
    public String toString() {
        return "LocationVO{" +
                "suggestions=" + suggestions +
                ", term='" + term + '\'' +
                ", moresuggestions='" + moresuggestions + '\'' +
                '}';
    }
}
