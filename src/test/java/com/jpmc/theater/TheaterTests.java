package com.jpmc.theater;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TheaterTests {
    @Test
    void totalFeeForCustomer() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        Customer john = new Customer("John Doe", "id-12345");
        Reservation reservation = theater.reserve(john, 2, 4);
        assertEquals(reservation.totalFee(), 40);
    }

    @Test
    void printMovieSchedule() {
        //Given
        Theater theater = new Theater(LocalDateProvider.singleton());
        // Keep current System.out with us
        PrintStream oldOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        //when
        theater.printSchedule();

        // then
        // Reset the System.out
        System.setOut(oldOut);
        String output = new String(outContent.toByteArray());
        assertTrue(output.contains("Turning Red (1 hour 25 minutes)"));
    }

    @Test
    void printMovieScheduleInJson() {
        // Keep current System.out with us
        PrintStream oldOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Theater theater = new Theater(LocalDateProvider.singleton());

        //When
        theater.printScheduleInJson();

        //then
        System.setOut(oldOut);
        String output = new String(outContent.toByteArray());
        assertTrue(output.contains("movie\":{\"title\":\"Turning Red\",\"runningTime\":5100.000000000,\"ticketPrice\":11.0,\"specialCode\":0}"));
    }
}