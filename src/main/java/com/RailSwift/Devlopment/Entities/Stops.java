package com.RailSwift.Devlopment.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "Stops")
@Table(name = "stops")
public class Stops {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "stop_id")
    private int stopId;

    @ManyToOne
    @JoinColumn(name = "station_id", nullable = false)
    private Station station;

    @Column(name = "stop_no", nullable = false)
    private int stopNo;

    @Column(name = "arrival_time", nullable = false)
    private LocalDateTime arrivalTime;

    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departureTime;

    public Stops() {
    }

    public Stops(final Station station, final int stopNo, final LocalDateTime arrivalTime, final LocalDateTime departureTime) {
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

    public LocalDateTime getArrivalTime() {
        return this.arrivalTime;
    }

    public void setArrivalTime(final LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalDateTime getDepartureTime() {
        return this.departureTime;
    }

    public void setDepartureTime(final LocalDateTime departureTime) {
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
