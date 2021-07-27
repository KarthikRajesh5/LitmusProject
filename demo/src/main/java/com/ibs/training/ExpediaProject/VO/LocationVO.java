package com.ibs.training.ExpediaProject.VO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationVO {
    private List<SuggestionVO> suggestions;

    public List<SuggestionVO> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<SuggestionVO> suggestions) {
        this.suggestions = suggestions;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "suggestions=" + suggestions +
                '}';
    }
}
