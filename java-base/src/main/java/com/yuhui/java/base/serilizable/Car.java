package com.yuhui.java.base.serilizable;

import java.io.Serializable;

public class Car implements Serializable {

    private static final long serialVersionUID = -1097124649032375441L;

    private static String AGE = "269";
    private String name;
    private String color;
    transient private String car;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", car='" + car + '\'' +
                '}';
    }
}
