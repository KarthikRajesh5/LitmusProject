package com.ibs.training.ExpediaProject.VO;

import java.util.List;

public class SuggestionVO {

    private List<EntitiesVO> entities;

    public List<EntitiesVO> getEntities() {
        return entities;
    }

    public void setEntities(List<EntitiesVO> entities) {
        this.entities = entities;
    }

    @Override
    public String toString() {
        return "Suggestion{" +
                "entities=" + entities +
                '}';
    }
}
