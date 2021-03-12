package com.epam.task.sixth.entities;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Client implements Runnable {
    private final static Lock LOCK = new ReentrantLock();
    private int pointX;
    private int pointY;
    private int id;
    private boolean served = false;

    public boolean isServed() {
        return served;
    }

    @Override
    public void run() {
        ControlRoom controlRoom = ControlRoom.getInstance();
        try {
            controlRoom.process(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getPointX() {
        return pointX;
    }

    public int getPointY() {
        return pointY;
    }

    public int getId() {
        return id;
    }

    public void setServed(boolean served) {
        this.served = served;
    }

}
