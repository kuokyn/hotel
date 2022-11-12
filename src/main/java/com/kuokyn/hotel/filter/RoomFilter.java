package com.kuokyn.hotel.filter;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;

public class RoomFilter {

    private String phrase;

    @Positive
    private int numberOfPeople;

    private String reservationStartDate;
    private String reservationEndDate;


    @PositiveOrZero
    private Float roomNumber;



    @PositiveOrZero
    private Float maxPrice;


    public boolean isEmpty(){
       // return StringUtils.isEmpty(phrase) && minPrice == null && minPrice == null;
        return numberOfPeople <1 && reservationEndDate == null && reservationStartDate == null;
    }

    public void clear(){
        this.numberOfPeople = 0;
        this.reservationStartDate=null;
        this.reservationEndDate=null;
    }


    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public String getReservationStartDate() {
        return reservationStartDate;
    }

    public void setReservationStartDate(String reservationStartDate) {
        this.reservationStartDate = reservationStartDate;
    }

    public String getReservationEndDate() {
        return reservationEndDate;
    }

    public void setReservationEndDate(String reservationEndDate) {
        this.reservationEndDate = reservationEndDate;
    }
}
