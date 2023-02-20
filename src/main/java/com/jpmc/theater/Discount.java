package com.jpmc.theater;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Discount {
    private int specialMovieCode;
    private double baseFee = 0;
    private int sequenceOfTheDay;
    private LocalDateTime showStartTime;
    private static int MOVIE_CODE_SPECIAL = 1;
    private static LocalTime startTime = LocalTime.parse( "11:00:00" );
    private static LocalTime endTime = LocalTime.parse( "16:00:00" );

    Discount(Showing showing) {
        this.specialMovieCode = showing.getMovie().getSpecialCode();
        this.baseFee = showing.getMovie().getTicketPrice();
        this.sequenceOfTheDay = showing.getSequenceOfTheDay();
        this.showStartTime = showing.getStartTime();
    }

    double calculateDiscount() {
        // DISCOUNT 1: Movie has a special code giving a 20% discount
        double specialDiscount = 0;
        //LocalTime startTime = LocalTime.parse( "11:00:00" );
        //LocalTime endTime = LocalTime.parse( "16:00:00" );
        int DAY = 7;
        if (MOVIE_CODE_SPECIAL == specialMovieCode) {
            specialDiscount = baseFee * 0.2;  // 20% discount for special movie
        }

        // DISCOUNT 2: Movie is of a certain sequence number giving fixed discount
        double sequenceDiscount = 0;
        if (sequenceOfTheDay == 1) {
            sequenceDiscount = 3; // $3 discount for 1st show
        } else if (sequenceOfTheDay == 2) {
            sequenceDiscount = 2; // $2 discount for 2nd show
        }

        // DISCOUNT 3: Movie falls within time window giving 25% discount
        double additionalDiscount = 0;
        if(showStartTime.toLocalTime().isAfter(startTime) && showStartTime.toLocalTime().isBefore(endTime) ) {
            additionalDiscount = baseFee * .25;
        }

        // DISCOUNT 4: Movie is on certain day of month giving fixed discount
        double dayMonthDiscount = 0;
        if(showStartTime.getDayOfMonth() == DAY) {
            dayMonthDiscount = 1;
        }

        // biggest discount wins
        return Math.max(specialDiscount, Math.max(sequenceDiscount,
                                Math.max(additionalDiscount, dayMonthDiscount)));
    }
}
