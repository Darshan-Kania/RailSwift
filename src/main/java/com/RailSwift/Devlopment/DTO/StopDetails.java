package com.RailSwift.Devlopment.DTO;

import java.time.LocalTime;

public class StopDetails {
    private int stationId;
    private int stopNo;
    private LocalTime arrivalTime;
    private LocalTime departureTime;

    public StopDetails() {
    }

    public StopDetails(final int stationId, final int stopNo, final LocalTime arrivalTime, final LocalTime departureTime) {
        this.stationId = stationId;
        this.stopNo = stopNo;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }

    public int getStationId() {
        return this.stationId;
    }

    public void setStationId(final int stationId) {
        this.stationId = stationId;
    }

    public int getStopNo() {
        return this.stopNo;
    }

    public void setStopNo(final int stopNo) {
        this.stopNo = stopNo;
    }

    public LocalTime getArrivalTime() {
        return this.arrivalTime;
    }

    public void setArrivalTime(final LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalTime getDepartureTime() {
        return this.departureTime;
    }

    public void setDepartureTime(final LocalTime departureTime) {
        this.departureTime = departureTime;
    }
}
