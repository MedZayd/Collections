package org.collectors.dto;

import java.util.List;

public class Category extends Entity {
    private List<Quality> qualities;

    public Category() {
    }

    public Category(Long id, String name) {
        super(id, name);
    }

    public List<Quality> getQualities() {
        return qualities;
    }

    public void setQualities(List<Quality> qualities) {
        this.qualities = qualities;
    }

    @Override
    public String toString() {
        return "Category{id=" + this.getId() +
                ", name='" + this.getName() +
                ", qualities=" + qualities +
                '}';
    }
}
