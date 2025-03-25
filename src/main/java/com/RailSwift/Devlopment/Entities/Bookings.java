package com.RailSwift.Devlopment.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bookings")
public class Bookings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "src_station_id", nullable = false)
    private Station src;

    @ManyToOne
    @JoinColumn(name = "dest_station_id", nullable = false)
    private Station dest;

    @ManyToOne
    @JoinColumn(name = "train_id", nullable = false)
    private Train train;

    @Column(name = "booking_date", nullable = false)
    private LocalDateTime bookingDate;

    @Column(name = "journey_date", nullable = false)
    private LocalDateTime journeyDate;

    @Column(name = "no_of_passengers", nullable = false)
    private int noOfPassengers;

    @Column(name = "total_fare", nullable = false)
    private double totalFare;

    @ElementCollection
    @CollectionTable(name = "booking_seats", joinColumns = @JoinColumn(name = "booking_id"))
    @Column(name = "seat_number")
    private List<Integer> seatsList;

    @Embedded
    private List<Passenger> passengerList;

    public Bookings() {
    }

    public Bookings(Users user, Station src, Station dest, Train train,
                    LocalDateTime bookingDate, LocalDateTime journeyDate,
                    int noOfPassengers, double totalFare, List<Integer> seatsList,
                    List<Passenger> passengerList) {
        this.user = user;
        this.src = src;
        this.dest = dest;
        this.train = train;
        this.bookingDate = bookingDate;
        this.journeyDate = journeyDate;
        this.noOfPassengers = noOfPassengers;
        this.totalFare = totalFare;
        this.seatsList = seatsList;
        this.passengerList = passengerList;
    }

    public Long getBookingId() {
        return this.bookingId;
    }

    public void setBookingId(final Long bookingId) {
        this.bookingId = bookingId;
    }

    public Users getUser() {
        return this.user;
    }

    public void setUser(final Users user) {
        this.user = user;
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

    public Train getTrain() {
        return this.train;
    }

    public void setTrain(final Train train) {
        this.train = train;
    }

    public LocalDateTime getBookingDate() {
        return this.bookingDate;
    }

    public void setBookingDate(final LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public LocalDateTime getJourneyDate() {
        return this.journeyDate;
    }

    public void setJourneyDate(final LocalDateTime journeyDate) {
        this.journeyDate = journeyDate;
    }

    public int getNoOfPassengers() {
        return this.noOfPassengers;
    }

    public void setNoOfPassengers(final int noOfPassengers) {
        this.noOfPassengers = noOfPassengers;
    }

    public double getTotalFare() {
        return this.totalFare;
    }

    public void setTotalFare(final double totalFare) {
        this.totalFare = totalFare;
    }

    public List<Integer> getSeatsList() {
        return this.seatsList;
    }

    public void setSeatsList(final List<Integer> seatsList) {
        this.seatsList = seatsList;
    }

    public List<Passenger> getPassengerList() {
        return this.passengerList;
    }

    public void setPassengerList(final List<Passenger> passengerList) {
        this.passengerList = passengerList;
    }

    @Override
    public String toString() {
        return "Bookings{" +
                "bookingDate=" + bookingDate +
                ", bookingId=" + bookingId +
                ", journeyDate=" + journeyDate +
                ", noOfPassengers=" + noOfPassengers +
                ", totalFare=" + totalFare +
                ", seatsList=" + seatsList +
                ", passengerList=" + passengerList +
                '}';
    }
}
