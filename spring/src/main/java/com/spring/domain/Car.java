package com.spring.domain;

/**
 * Created by alibeibei on 2017/8/11.
 */
public class Car {
    private String name;
    private double price;

    // 为Car类 提供构造方法
    public Car(String name, double price) {
        super();
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
