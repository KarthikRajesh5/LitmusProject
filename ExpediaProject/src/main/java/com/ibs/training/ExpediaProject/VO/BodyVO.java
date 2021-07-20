package com.ibs.training.ExpediaProject.VO;


public class BodyVO {
    private SearchResultVO searchResults;

    public SearchResultVO getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(SearchResultVO searchResults) {
        this.searchResults = searchResults;
    }

    @Override
    public String toString() {
        return "Body{" +
                "searchResults=" + searchResults +
                '}';
    }
}
