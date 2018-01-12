package com.marre2retard.mar2retard.models;

import java.sql.Time;

/**
 * Created by gasmi on 29/12/2017.
 */

public class Train {
    private String TrainNumber;
    private String DepurtStation;
    private String ArrivalStation;
    private String DepartTime;
    private String ArrivalTime;

    public Train(String trainNumber, String depurtStation, String arrivalStation, String departTime, String arrivalTime) {
        TrainNumber = trainNumber;
        DepurtStation = depurtStation;
        ArrivalStation = arrivalStation;
        DepartTime = departTime;
        ArrivalTime = arrivalTime;
    }

    // Constructor Train to shared
    public Train(String trainNumber, String depurtStation, String arrivalStation,
                 String departTime, String arrivalTime, Time reelDepart, Time reelArrival) {
        TrainNumber = trainNumber;
        DepurtStation = depurtStation;
        ArrivalStation = arrivalStation;
        DepartTime = departTime;
        ArrivalTime = arrivalTime;

    }

    public String getTrainNumber() {
        return TrainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        TrainNumber = trainNumber;
    }

    public String getDepurtStation() {
        return DepurtStation;
    }

    public void setDepurtStation(String depurtStation) {
        DepurtStation = depurtStation;
    }

    public String getArrivalStation() {
        return ArrivalStation;
    }

    public void setArrivalStation(String arrivalStation) {
        ArrivalStation = arrivalStation;
    }

    public String getDepartTime() {
        return DepartTime;
    }

    public void setDepartTime(String departTime) {
        DepartTime = departTime;
    }

    public String getArrivalTime() {
        return ArrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        ArrivalTime = arrivalTime;
    }

    @Override
    public String toString() {
        return "Train{" +
                "TrainNumber='" + TrainNumber + '\'' +
                ", DepurtStation='" + DepurtStation + '\'' +
                ", ArrivalStation='" + ArrivalStation + '\'' +
                ", DepartTime=" + DepartTime +
                ", ArrivalTime=" + ArrivalTime +
                '}';
    }
}
