package com.RailSwift.Devlopment.Entities;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "Train")
@Table(name = "train")
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "train_sequence")
    @SequenceGenerator(name = "train_sequence", sequenceName = "train_sequence", initialValue = 12000, allocationSize = 1)
    @Column(name = "train_no")
    private Long trainNo;

    @Column(name = "train_name", nullable = false, unique = true)
    private String trainName;

    @Column(name = "capacity", nullable = false, columnDefinition = "integer default 0")
    private int capacity;

    @Enumerated(EnumType.STRING)
    private TrainType trainType;

    @Column(name = "fare_per_stops", nullable = false)
    private double farePerStops;

    @ElementCollection
    private List<ActiveDays> activeDaysList;

    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "train_no")
    private List<Stops> stopsList;

    public Train() {
    }

    public Train(final Long trainNo, final String trainName, final int capacity, final TrainType trainType, final double farePerStops, final List<ActiveDays> activeDaysList) {
        this.trainNo = trainNo;
        this.trainName = trainName;
        this.capacity = capacity;
        this.trainType = trainType;
        this.farePerStops = farePerStops;
        this.activeDaysList = activeDaysList;
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

    public int getCapacity() {
        return this.capacity;
    }

    public void setCapacity(final int capacity) {
        this.capacity = capacity;
    }

    public TrainType getTrainType() {
        return this.trainType;
    }

    public void setTrainType(final TrainType trainType) {
        this.trainType = trainType;
    }

    public double getFarePerStops() {
        return this.farePerStops;
    }

    public void setFarePerStops(final double farePerStops) {
        this.farePerStops = farePerStops;
    }

    public List<ActiveDays> getActiveDaysList() {
        return this.activeDaysList;
    }

    public void setActiveDaysList(final List<ActiveDays> activeDaysList) {
        this.activeDaysList = activeDaysList;
    }

    public List<Stops> getStopsList() {
        return this.stopsList;
    }

    public void setStopsList(final List<Stops> stopsList) {
        this.stopsList = stopsList;
    }

    public void addStops(Stops stops) {
        stopsList.add(stops);
    }

    @Override
    public String toString() {
        return "Train{" +
                "trainNo=" + trainNo +
                ", trainName='" + trainName + '\'' +
                ", capacity=" + capacity +
                ", trainType=" + trainType +
                ", farePerStops=" + farePerStops +
                ", activeDaysList=" + activeDaysList +
                '}';
    }
}
