package com.quoteoftheday.raccoonapps.diploma.mode.pojo;

import com.amulyakhare.textdrawable.util.ColorGenerator;

public class Method {

    private String name;
    private int color;

    public Method(String name) {
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
