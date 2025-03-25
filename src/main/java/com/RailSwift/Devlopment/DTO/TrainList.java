package com.RailSwift.Devlopment.DTO;

import com.RailSwift.Devlopment.Entities.Station;
import com.RailSwift.Devlopment.Entities.TrainType;

public class TrainList {
    private Long trainNo;
    private String trainName;
    private TrainType trainType;
    private Station src;
    private Station dest;

    public TrainList() {
    }

    public TrainList(final Long trainNo, final String trainName, final TrainType trainType, final Station src, final Station dest) {
        this.trainNo = trainNo;
        this.trainName = trainName;
        this.trainType = trainType;
        this.src = src;
        this.dest = dest;
    }

    public Long getTrainNo() {
        return this.trainNo;
    }

    public void setTrainNo(final Long trainNo) {
        this.trainNo = trainNo;
    }

    public String getTrainName() {
        return this.trainName;
    }

    public void setTrainName(final String trainName) {
        this.trainName = trainName;
    }

    public TrainType getTrainType() {
        return this.trainType;
    }

    public void setTrainType(final TrainType trainType) {
        this.trainType = trainType;
    }

    public Station getSrc() {
        return this.src;
    }

    public void setSrc(final Station src) {
        this.src = src;
    }

    public Station getDest() {
        return this.dest;
    }

    public void setDest(final Station dest) {
        this.dest = dest;
    }

    @Override
    public String toString() {
        return "TrainList{" +
                "trainNo=" + trainNo +
                ", trainName='" + trainName + '\'' +
                ", trainType='" + trainType + '\'' +
                ", src=" + src +
                ", dest=" + dest +
                '}';
    }
}
