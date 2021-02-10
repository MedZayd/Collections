package org.collectors.dto;

public class Quality extends Entity {

    public Quality() {
    }

    public Quality(Long id, String name) {
        super(id, name);
    }

    @Override
    public String toString() {
        return "Quality{id=" + this.getId() +
                "name=" + this.getName() + "}";
    }
}
