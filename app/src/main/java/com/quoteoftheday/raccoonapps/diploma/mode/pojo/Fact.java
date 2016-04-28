package com.quoteoftheday.raccoonapps.diploma.mode.pojo;

import java.util.UUID;

public class Fact {

    private UUID id;

    private String value;

    private String description;

    private UUID factClass;

    public Fact() {
        this.id = UUID.randomUUID();
    }

    public Fact(String value, String description, String factClass) {
        this();
        this.value = value;
//        this.factClass = UUID.fromString(factClass);
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public UUID getFactClass() {
        return factClass;
    }
}
