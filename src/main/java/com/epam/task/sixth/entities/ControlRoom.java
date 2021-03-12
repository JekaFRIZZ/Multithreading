package com.epam.task.sixth.entities;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ControlRoom {
    private final static int REQUIRED_DISTANCE = 1;
    private final static String FILE_TAXIS = "src/main/resources/taxis.json";
    private final static Lock LOCK = new ReentrantLock();
    private static ControlRoom instance;

    private ControlRoom() {
    }

    public static ControlRoom getInstance() {
        if (instance == null) {
            try {
                LOCK.lock();
                if (instance == null) {
                    instance = new ControlRoom();
                }
            }finally {
                LOCK.unlock();
            }
        }
        return instance;
    }

    private List<Taxi> readTaxis() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Taxis taxisWrapper = mapper.readValue(new File(FILE_TAXIS),Taxis.class);
        return taxisWrapper.getTaxis();
    }

    public void process(Client client) throws IOException {
        LOCK.lock();

        List<Taxi> taxis = readTaxis();

        for(Taxi taxi: taxis) {
            if(validDistance(taxi,client) && !client.isServed() && !taxi.isBusiness()) {
                taxi.setBusiness(true);
                client.setServed(true);
                System.out.println(client.getId() + " is served taxi " + taxi.getId());
                taxi.setBusiness(false);
                break;
            }
        }
        LOCK.unlock();
    }

    private boolean validDistance(Taxi taxi,Client client) {
        int pointX = client.getPointX();
        int pointY = client.getPointY();

        return Math.pow((taxi.getPointX() - pointX),2) + Math.pow((taxi.getPointY() - pointY),2) <
                Math.pow(REQUIRED_DISTANCE,2);
    }

}
