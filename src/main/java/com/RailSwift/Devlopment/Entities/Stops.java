package com.RailSwift.Devlopment.Entities;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity(name = "Stops")
@Table(name = "stops")
public class Stops {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stop_id")
    private int stopId;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "station_id", nullable = false)
    private Station station;

    @Column(name = "stop_no", nullable = false)
    private int stopNo;

    @Column(name = "arrival_time", nullable = false)
    private LocalTime arrivalTime;

    @Column(name = "departure_time", nullable = false)
    private LocalTime departureTime;

    public Stops() {
    }

    public Stops(final Station station, final int stopNo, final LocalTime arrivalTime, final LocalTime departureTime) {
        this.station = station;
        this.stopNo = stopNo;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }

    public int getStopId() {
        return this.stopId;
    }

    public void setStopId(final int stopId) {
        this.stopId = stopId;
    }

    public Station getStation() {
        return this.station;
    }

    public void setStation(final Station station) {
        this.station = station;
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

    @Override
    public String toString() {
        return "Stops{" +
                "stopId=" + stopId +
                ", station=" + station +
                ", stopNo=" + stopNo +
                ", arrivalTime=" + arrivalTime +
                ", departureTime=" + departureTime +
                '}';
    }
}
