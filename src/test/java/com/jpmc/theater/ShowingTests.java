package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShowingTests {
    @Test
    void specialMovieAfter4pmShouldHave20PercentDiscount() {
        // When
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.parse("18:00:00")));
        //when
        double discountedTicketPrice = showing.getDiscountedTicketPrice();
        // Then
        assertEquals(10, discountedTicketPrice);
    }

    @Test
    void specialMovieBefore11amShouldHave20PercentDiscount() {
        // When
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.parse("10:00:00")));
        //when
        double discountedTicketPrice = showing.getDiscountedTicketPrice();
        // Then
        assertEquals(10, discountedTicketPrice);
    }

    @Test
    void specialMovieAfter11amBefore4pmShouldHave25PercentDiscount() {
        // Given
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.parse("12:00:00")));
        // When
        double discountedTicketPrice = showing.getDiscountedTicketPrice();
        // Then
        assertEquals(9.375, discountedTicketPrice);
    }

    @Test
    void nonSpecialMovieAfter11amBefore4pmShouldHave20PercentDiscount() {
        // Given
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 0);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.parse("12:00:00")));
        // When
        double discountedTicketPrice = showing.getDiscountedTicketPrice();
        // Then
        assertEquals(9.375, discountedTicketPrice);
    }

    @Test
    void movieWithSequenceNumberOneShouldHaveDiscount() {
        // Given
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 0);
        Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(LocalDate.now(), LocalTime.parse("10:00:00")));
        //when
        double discountedTicketPrice = showing.getDiscountedTicketPrice();
        // Then
        assertEquals(9.5, discountedTicketPrice);
    }

    @Test
    void movieWithSequenceNumberThreeShouldHaveDiscount() {
        // Given
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 0);
        Showing showing = new Showing(spiderMan, 2, LocalDateTime.of(LocalDate.now(), LocalTime.parse("10:00:00")));
        // When
        double discountedTicketPrice = showing.getDiscountedTicketPrice();
        // Then
        assertEquals(10.5, discountedTicketPrice);
    }

    @Test
    void movieOnCertainDayOfMonthShouldHaveDiscount() {
        // Given
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 0);
        Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(LocalDateProvider.currentDate(), LocalTime.of(9, 0)));
        //when
        double discountedTicketPrice = showing.getDiscountedTicketPrice();
        //then
        assertEquals(11.5, discountedTicketPrice);
    }

    @Test
    void discountShouldEqualSequenceDiscount() {
        // Given
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),9, 0);
        Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(LocalDate.now(), LocalTime.parse("12:00:00")));
        //when
        double discountedTicketPrice = showing.getDiscountedTicketPrice();
        //Then
        assertEquals(6, discountedTicketPrice);
    }

    @Test
    void testShowingInJson() {
        // Given
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),9, 0);
        Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(LocalDate.now(), LocalTime.parse("12:00:00")));
        // when
        String showingInJson = showing.getShowingInJson();
        System.out.println(showingInJson);
        //Then

    }
}
