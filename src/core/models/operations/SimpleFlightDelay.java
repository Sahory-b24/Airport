/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.operations;

import core.models.Flight;
import core.models.utils.FlightDelay;
import java.time.LocalDateTime;

/**
 *
 * @author ujhuh
 */
public class SimpleFlightDelay  implements FlightDelay {
    // Metodo que implementa una interfaz con el fin de aplicar un delay a un vuelo
    @Override
    public boolean applyDelay(Flight flight, int hours, int minutes) {
         if (flight == null || (hours == 0 && minutes == 0)) { 
            return false;
        }
        LocalDateTime newDepartureDate = flight.getDepartureDate().plusHours(hours).plusMinutes(minutes);
        flight.setDepartureDate(newDepartureDate); 
        return true;}

}
