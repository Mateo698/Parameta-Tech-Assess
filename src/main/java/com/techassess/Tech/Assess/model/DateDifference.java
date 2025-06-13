package com.techassess.Tech.Assess.model;
import lombok.Getter;

import java.time.LocalDate;
import java.time.Period;

@Getter
public class DateDifference {
    private final int years;
    private final int months;
    private final int days;

    public DateDifference(LocalDate startDate, LocalDate endDate) {
        Period period = Period.between(startDate, endDate);
        this.years = period.getYears();
        this.months = period.getMonths();
        this.days = period.getDays();
    }

    public DateDifference(int years, int months, int days) {
        this.years = years;
        this.months = months;
        this.days = days;
    }

    @Override
    public String toString() {
        return String.format("%d years, %d months, %d days", years, months, days);
    }
}