package com.RailSwift.Devlopment.DTO;

import com.RailSwift.Devlopment.Entities.Station;
import com.RailSwift.Devlopment.Entities.Train;

public class TrainDeatils {
    Train train;
    Station src;
    Station dest;
    double fare;

    public TrainDeatils() {
    }

    public TrainDeatils(final Train train, final Station src, final Station dest, final double fare) {
        this.train = train;
        this.src = src;
        this.dest = dest;
        this.fare = fare;
    }

    public Train getTrain() {
        return this.train;
    }

    public void setTrain(final Train train) {
        this.train = train;
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

    public double getFare() {
        return this.fare;
    }

    public void setFare(final double fare) {
        this.fare = fare;
    }
}
