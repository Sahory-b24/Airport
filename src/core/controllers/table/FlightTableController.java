/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.table;

import core.controllers.FlightController;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Flight;
import core.models.storage.FlightRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sahory Blanco
 */
public class FlightTableController {
    public static Response updateFlightsTable(DefaultTableModel model) {
        try {
            model.setRowCount(0);
            ArrayList<Flight> flights = FlightRepository.getInstance().getAllFlightsSorted();

            if (flights == null || flights.isEmpty()) {
                return new Response("Flight list is empty.", Status.NO_CONTENT);
            }
            
            for (Flight f : flights) {
                
                LocalDateTime arrivalDateTime = FlightController.getCalculatedArrivalDateForFlight(f);
                model.addRow(new Object[]{
                    f.getId(),
                    f.getPlane().getId(),
                    f.getDepartureLocation().getAirportId(),
                    f.getArrivalLocation().getAirportId(),
                    f.getDepartureDate(),
                    arrivalDateTime,
                    f.getPlane().getId(),
                    f.getNumPassengers()
                });
            }

            return new Response("Flight list updated successfully.", Status.OK);
        } catch (Exception e) {
            return new Response("Unexpected error: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }
}
