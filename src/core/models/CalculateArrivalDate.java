/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

import java.time.LocalDateTime;

/**
 *
 * @author ujhuh
 */
public class CalculateArrivalDate {
    private Flight flight;
     public LocalDateTime calculateArrivalDate() {
        return flight.getDepartureDate().plusHours(flight.getHoursDurationArrival()).plusHours(flight.getHoursDurationArrival()).plusMinutes(flight.getMinutesDurationScale()).plusMinutes(flight.getMinutesDurationArrival());
    }
}
