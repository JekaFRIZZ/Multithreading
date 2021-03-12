package com.epam.task.sixth.entities;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Taxi {
    private final static Lock LOCK = new ReentrantLock();

    private double pointX;
    private double pointY;
    private int id;
    private boolean business;

    public double getPointX() {
        return pointX;
    }

    public double getPointY() {
        return pointY;
    }

    public int getId() {
        return id;
    }

    public boolean isBusiness() {
        return business;
    }

    public void setBusiness(boolean business) {
        this.business = business;
    }

}
