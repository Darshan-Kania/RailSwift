package com.RailSwift.Devlopment.Entities;

import jakarta.persistence.*;

@Entity(name = "Station")
@Table(name = "station")
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "station_id")
    private int stationId;
    @Column(name = "station_name", nullable = false)
    private String stationName;
    @Column(name = "station_code", nullable = false, unique = true)
    private String stationCode;
    @Column(name = "city", nullable = false)
    private String city;

    public Station() {
    }

    public Station(final String stationName, final String stationCode, final String city) {
        this.stationName = stationName;
        this.stationCode = stationCode;
        this.city = city;
    }

    public int getStationId() {
        return this.stationId;
    }

    public void setStationId(final int stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return this.stationName;
    }

    public void setStationName(final String stationName) {
        this.stationName = stationName;
    }

    public String getStationCode() {
        return this.stationCode;
    }

    public void setStationCode(final String stationCode) {
        this.stationCode = stationCode;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Station{" + "stationId=" + stationId + ", stationName='" + stationName + '\'' + ", stationCode='" + stationCode + '\'' + ", city='" + city + '\'' + '}';
    }
}
