package com.quoteoftheday.raccoonapps.diploma.mode.pojo;

import com.amulyakhare.textdrawable.util.ColorGenerator;

public class BaseHierarchies {

    private String name;
    private int color;

    public BaseHierarchies(String name) {
        this.name = name;
        this.color = ColorGenerator.MATERIAL.getRandomColor();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColor() {
        return color;
    }
}
