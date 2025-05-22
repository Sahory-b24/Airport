/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.operations;

import core.models.Flight;
import core.models.utils.FlightArrivalCalculator;
import java.time.LocalDateTime;

/**
 *
 * @author ujhuh
 */
public class StandardFlightArrivalCalculator implements FlightArrivalCalculator{

    @Override
    public LocalDateTime calculate(Flight flight) {
        
        LocalDateTime departure = flight.getDepartureDate();
        long totalHours = flight.getHoursDurationArrival(); 
        long totalMinutes = flight.getMinutesDurationArrival();

        if (flight.getScaleLocation() != null) {
            totalHours += flight.getHoursDurationScale();
            totalMinutes += flight.getMinutesDurationScale();
        }
        return departure.plusHours(totalHours).plusMinutes(totalMinutes);
    }
    
}
