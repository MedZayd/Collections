package org.collectors.dto;

import java.util.List;

public class Family extends Entity {
    private List<Category> categories;

    public Family() {
    }

    public Family(Long id, String name) {
        super(id, name);
    }

    public Family(Long id, String name, Entity entity) {
        super(id, name, entity);
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Family{id=" + this.getId() +
                ", name='" + this.getName() +
                ", categories=" + categories +
                '}';
    }
}
