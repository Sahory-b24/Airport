/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Flight;
import core.models.Location;
import core.models.Passenger;
import core.models.Plane;
import core.models.operations.SimpleFlightDelay;
import core.models.operations.StandardFlightArrivalCalculator;
import core.models.storage.FlightRepository;
import core.models.storage.LocationRepository;
import core.models.storage.PassengerRepository;
import core.models.storage.PlaneRepository;
import core.models.utils.FlightArrivalCalculator;
import core.models.utils.FlightDelay;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author Sahory Blanco
 */
public class FlightController {
    private static final FlightArrivalCalculator arrivalCalculator = new StandardFlightArrivalCalculator();
    private static final FlightDelay delayStrategy = new SimpleFlightDelay();
    private static final Pattern FLIGHT_ID_PATTERN = Pattern.compile("[A-Z]{3}\\d{3}");


    public static Response createFlight(
        String id,
        String planeId,
        String departureLocationId,
        String arrivalLocationId,
        String scaleLocationId,
        String departureDateStr,
        String departureTimeStr,
        String hoursDurationArrival,
        String minutesDurationArrival,
        String hoursDurationScale,
        String minutesDurationScale
    ) {
        try {
            // Validación básica de ID
            if (id == null || !FLIGHT_ID_PATTERN.matcher(id).matches()) {
                return new Response("Invalid flight ID. Must be 3 uppercase letters followed by 3 digits (e.g. ABC123).", Status.BAD_REQUEST);
            }

            if (planeId == null || planeId.trim().isEmpty() ||
                departureLocationId == null || departureLocationId.trim().isEmpty() ||
                arrivalLocationId == null || arrivalLocationId.trim().isEmpty() ||
                departureDateStr == null || departureDateStr.trim().isEmpty()) {
                return new Response("Plane, locations and departure date are required.", Status.BAD_REQUEST);
            }

            boolean hasScale = scaleLocationId != null && !scaleLocationId.trim().isEmpty();

            // Parsear duración
            int hArr, mArr, hScale, mScale;
            try {
                hArr = Integer.parseInt(hoursDurationArrival);
                mArr = Integer.parseInt(minutesDurationArrival);
                hScale = Integer.parseInt(hoursDurationScale);
                mScale = Integer.parseInt(minutesDurationScale);
            } catch (NumberFormatException e) {
                return new Response("Duration times must be numeric.", Status.BAD_REQUEST);
            }

            long totalMinutes = hArr * 60L + mArr;
            if (hasScale) totalMinutes += hScale * 60L + mScale;

            if (totalMinutes <= 0) {
                return new Response("Total flight time must be greater than 00:00.", Status.BAD_REQUEST);
            }

            if (!hasScale && (hScale > 0 || mScale > 0)) {
                return new Response("Scale time must be 0 if there is no scale location.", Status.BAD_REQUEST);
            }

            // Parsear fecha
            LocalDateTime departureDate;
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d'T'H:m");
                departureDate = LocalDateTime.parse(departureDateStr + "T" + departureTimeStr, formatter);

                if (departureDate.isBefore(LocalDateTime.now())) {
                    return new Response("La fecha de salida debe ser futura o actual.", Status.BAD_REQUEST);
                }
            } catch (DateTimeParseException e) {
                return new Response("Formato de fecha u hora inválido. Usa YYYY-MM-DD y HH:mm.", Status.BAD_REQUEST);
            }
            int hoursArrival, minutesArrival;
            try {
                hoursArrival = Integer.parseInt(hoursDurationArrival);
                minutesArrival = Integer.parseInt(minutesDurationArrival);
                if (hoursArrival < 0 || minutesArrival < 0 || minutesArrival > 59) {
                    return new Response("Duración de llegada inválida.", Status.BAD_REQUEST);
                }
                if (hoursArrival == 0 && minutesArrival == 0) {
                    return new Response("La duración de llegada no puede ser cero.", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                return new Response("La duración de llegada debe ser numérica.", Status.BAD_REQUEST);
            }
            // Obtener instancias
            FlightRepository flightRepo = FlightRepository.getInstance();
            PlaneRepository planeRepo = PlaneRepository.getInstance();
            LocationRepository locationRepo = LocationRepository.getInstance();

            if (flightRepo.getFlight(id) != null) {
                return new Response("Flight with ID " + id + " already exists.", Status.BAD_REQUEST);
            }

            Plane plane = planeRepo.getPlane(planeId);
            if (plane == null) {
                return new Response("Plane not found.", Status.NOT_FOUND);
            }

            Location departureLoc = locationRepo.getLocation(departureLocationId);
            Location arrivalLoc = locationRepo.getLocation(arrivalLocationId);
            if (departureLoc == null || arrivalLoc == null) {
                return new Response("Departure or arrival location not found.", Status.NOT_FOUND);
            }

            if (departureLoc.equals(arrivalLoc)) {
                return new Response("Departure and arrival locations must be different.", Status.BAD_REQUEST);
            }

            Location scaleLoc = null;
            if (hasScale) {
                scaleLoc = locationRepo.getLocation(scaleLocationId);
                if (scaleLoc == null) {
                    return new Response("Scale location not found.", Status.NOT_FOUND);
                }
                if (scaleLoc.equals(departureLoc) || scaleLoc.equals(arrivalLoc)) {
                    return new Response("Scale location must be different from departure and arrival.", Status.BAD_REQUEST);
                }
            }

            // Crear vuelo
            Flight flight;
            if (hasScale) {
                flight = new Flight(id, plane, departureLoc, scaleLoc, arrivalLoc, departureDate,
                                    hArr, mArr, hScale, mScale);
            } else {
                flight = new Flight(id, plane, departureLoc, arrivalLoc, departureDate,
                                    hArr, mArr);
            }

            if (flightRepo.addFlight(flight)) {
                return new Response("Flight created successfully.", Status.CREATED, flight.clone());
            } else {
                return new Response("Failed to save flight.", Status.INTERNAL_SERVER_ERROR);
            }

        } catch (Exception e) {
            return new Response("Unexpected error: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }

    
    public static Response delayFlight(String flightId, int hours, int minutes) {
        
        if (flightId == null || flightId.trim().isEmpty()) {
            return new Response("Flight ID is required to delay it.", Status.BAD_REQUEST);
        }
        if (hours <= 0 && minutes <= 0) { 
            return new Response("The delay time (hours or minutes) must be greater than 00:00.", Status.BAD_REQUEST);
        }
        if (hours < 0 || minutes < 0) { 
            return new Response("Delay hours and minutes cannot be negative.", Status.BAD_REQUEST);
        }

        FlightRepository flightRepo = FlightRepository.getInstance();
        Flight flight = flightRepo.getFlight(flightId);

        if (flight == null) {
            return new Response("Flight with ID " + flightId + " not found.", Status.NOT_FOUND);
        }

        boolean success = delayStrategy.applyDelay(flight, hours, minutes);

        if (success) {
            return new Response("Flight " + flightId + " delayed successfully.", Status.OK, flight.clone()); //
        } else {
            return new Response("Could not apply delay to flight " + flightId + ".", Status.BAD_REQUEST);
        }
    }
    public static Response addPassengerToFlight(String passengerIdStr, String flightId) {
        try {
            if (flightId == null || flightId.trim().isEmpty()) {
                return new Response("Flight ID is required.", Status.BAD_REQUEST);
            }

            long passengerId;
            try {
                passengerId = Long.parseLong(passengerIdStr.trim());
            } catch (NumberFormatException e) {
                return new Response("Passenger ID must be numeric.", Status.BAD_REQUEST);
            }

            FlightRepository flightRepo = FlightRepository.getInstance();
            PassengerRepository passengerRepo = PassengerRepository.getInstance();

            Flight flight = flightRepo.getFlight(flightId);
            if (flight == null) {
                return new Response("Flight with ID " + flightId + " not found.", Status.NOT_FOUND);
            }

            Passenger passenger = passengerRepo.getPassenger(passengerId);
            if (passenger == null) {
                return new Response("Passenger with ID " + passengerId + " not found.", Status.NOT_FOUND);
            }

            if (flight.getNumPassengers() >= flight.getPlane().getMaxCapacity()) {
                return new Response("Flight " + flightId + " has reached its maximum passenger capacity.", Status.BAD_REQUEST);
            }

            if (passenger.getFlights().contains(flight)) {
                return new Response("Passenger is already assigned to this flight.", Status.BAD_REQUEST);
            }

            flight.addPassenger(passenger);
            passenger.addFlight(flight);

            return new Response("Passenger " + passengerId + " added to flight " + flightId + " successfully.", Status.OK, flight.clone());

        } catch (Exception e) {
            return new Response("Unexpected error: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        } 
    }
    
    public static Response getAllFlightsSortedByDate() {
        FlightRepository flightRepo = FlightRepository.getInstance();
        List<Flight> flights = flightRepo.getAllFlightsSorted();
        List<Flight> flightClones = new ArrayList<>();
        for (Flight f : flights) {
            flightClones.add(f.clone());
        }
        if (flightClones.isEmpty()) {
            return new Response("No flights found.", Status.NO_CONTENT);
        }
        return new Response("Flights retrieved successfully.", Status.OK, flightClones);
    }
    public static LocalDateTime getCalculatedArrivalDateForFlight(Flight flight) {
        if (flight == null) {
            return null;
        }
        return arrivalCalculator.calculate(flight);
    }
}
