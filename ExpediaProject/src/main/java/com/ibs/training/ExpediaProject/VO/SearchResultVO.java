package com.ibs.training.ExpediaProject.VO;

import java.util.List;

public class SearchResultVO {
    private List<ResultsVO> results;

    public List<ResultsVO> getResults() {
        return results;
    }

    public void setResults(List<ResultsVO> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "results=" + results +
                '}';
    }
}
