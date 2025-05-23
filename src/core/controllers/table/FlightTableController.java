/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.table;

import core.controllers.FlightController;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Flight;
import core.models.Passenger;
import core.models.storage.FlightRepository;
import core.models.storage.PassengerRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
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
    
    public static Response updateMyFlightsTable(String selected, DefaultTableModel model) {
        try {
            if (selected == null || selected.startsWith("Select")) {
                return new Response("Please select a passenger.", Status.BAD_REQUEST);
            }

            long passengerId;
            try {
                passengerId = Long.parseLong(selected.split(" ")[0]);
            } catch (NumberFormatException e) {
                return new Response("Invalid passenger ID format.", Status.BAD_REQUEST);
            }

            Passenger passenger = PassengerRepository.getInstance().getPassenger(passengerId);
            if (passenger == null) {
                return new Response("Passenger not found.", Status.NOT_FOUND);
            }

            ArrayList<Flight> flights = new ArrayList<>(passenger.getFlights());
            
            if (flights == null || flights.isEmpty()) {
                return new Response("This passenger has no flights.", Status.NO_CONTENT);
            }
            
            flights.sort(Comparator.comparing(Flight::getDepartureDate));
            model.setRowCount(0);
            
            for (Flight flight : flights) {
                model.addRow(new Object[]{
                    flight.getId(),
                    flight.getDepartureDate(),
                    flight.getMinutesDurationArrival()
                });
            }

            return new Response("Flights loaded successfully.", Status.OK);

        } catch (Exception e) {
            return new Response("Unexpected error: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }
}
