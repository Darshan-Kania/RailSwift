package com.RailSwift.Devlopment.Entities;

import jakarta.persistence.Embeddable;

@Embeddable
public class Passenger {
    private String name;
    private int age;
}
