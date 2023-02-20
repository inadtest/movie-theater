package com.jpmc.theater;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDateTime;

public class Showing {
    private Movie movie;
    private int sequenceOfTheDay;
    private LocalDateTime showStartTime;
    private Discount discount;

    public Showing(Movie movie, int sequenceOfTheDay, LocalDateTime showStartTime) {
        this.movie = movie;
        this.sequenceOfTheDay = sequenceOfTheDay;
        this.showStartTime = showStartTime;
        this.discount = new Discount(this);
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDateTime getStartTime() {
        return showStartTime;
    }

    public double getDiscountedTicketPrice() {
        return movie.getTicketPrice() - discount.calculateDiscount();
    }

    public int getSequenceOfTheDay() {
        return sequenceOfTheDay;
    }

    private double calculateFee(int audienceCount) {
        return this.getDiscountedTicketPrice() * audienceCount;
    }

    public String convertShowingToJsonFormat() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        String output = null;
        try {
            output = mapper.writeValueAsString(this);
            System.out.println(output);
        }
        catch (JsonProcessingException e) {
            System.out.println("Error processing Showing" + e.getMessage());
            e.printStackTrace();
        }
        return output;
    }
}
