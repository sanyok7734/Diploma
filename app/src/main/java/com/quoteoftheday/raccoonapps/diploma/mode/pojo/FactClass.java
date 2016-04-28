package com.quoteoftheday.raccoonapps.diploma.mode.pojo;

import java.util.UUID;

public class FactClass {

    private UUID id;

    private String key;

    String description;

    private boolean selected;

    public FactClass() {
        this.id = UUID.randomUUID();
    }

    public FactClass(String key, String description) {
        this();
        this.key = key;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public String getDescription() {
        return description;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return "FactClass{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

