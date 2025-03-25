package com.RailSwift.Devlopment.Entities;

import java.time.DayOfWeek;

public enum ActiveDays {
    Sunday,
    Monday,
    Tuesday,
    Wednesday,
    Thursday,
    Friday,
    Saturday;

    public DayOfWeek getDay() {
        switch (this) {
            case Sunday:
                return DayOfWeek.SUNDAY;
            case Monday:
                return DayOfWeek.MONDAY;
            case Tuesday:
                return DayOfWeek.TUESDAY;
            case Wednesday:
                return DayOfWeek.WEDNESDAY;
            case Thursday:
                return DayOfWeek.THURSDAY;
            case Friday:
                return DayOfWeek.FRIDAY;
            case Saturday:
                return DayOfWeek.SATURDAY;
            default:
                return null;
        }
    }
}
