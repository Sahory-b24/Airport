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
import core.models.utils.Add;
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

        if (id == null || !FLIGHT_ID_PATTERN.matcher(id.trim()).matches()) { 
            return new Response("Invalid flight ID. Must be 3 uppercase letters followed by 3 digits (e.g. ABC123).", Status.BAD_REQUEST);
        }
        id = id.trim(); // Usar el ID sin espacios extra

      
        if (planeId == null || planeId.trim().isEmpty() ||
            departureLocationId == null || departureLocationId.trim().isEmpty() ||
            arrivalLocationId == null || arrivalLocationId.trim().isEmpty() ||
            departureDateStr == null || departureDateStr.trim().isEmpty() ||
            departureTimeStr == null || departureTimeStr.trim().isEmpty()) {
            return new Response("Flight ID, Plane, Departure/Arrival Locations, Departure Date and Time are required.", Status.BAD_REQUEST);
        }

        boolean hasScale = scaleLocationId != null && !scaleLocationId.trim().isEmpty() && !scaleLocationId.equalsIgnoreCase("Location") && !scaleLocationId.equalsIgnoreCase("None");
        int hArr, mArr, hScale = 0, mScale = 0; 
       try {
            hArr = Integer.parseInt(hoursDurationArrival.trim());
            mArr = Integer.parseInt(minutesDurationArrival.trim());
            if (hArr < 0 || mArr < 0 || mArr > 59) {
                return new Response("Invalid arrival duration values (Hours >=0, 0 <= Minutes <= 59).", Status.BAD_REQUEST);
            }
            if (hArr == 0 && mArr == 0) {
                 return new Response("Arrival duration cannot be zero.", Status.BAD_REQUEST);
            }

            // Parseo y validación de duración de escala 
            if (hasScale) {
                // Si hay una escala seleccionada, las duraciones deben ser números válidos.
                if (hoursDurationScale == null || hoursDurationScale.trim().isEmpty() ||
                    minutesDurationScale == null || minutesDurationScale.trim().isEmpty()) {
                    return new Response("Scale duration (hours and minutes) must be provided if a scale location is selected.", Status.BAD_REQUEST);
                }
                hScale = Integer.parseInt(hoursDurationScale.trim());
                mScale = Integer.parseInt(minutesDurationScale.trim());
                if (hScale < 0 || mScale < 0 || mScale > 59) {
                    return new Response("Invalid scale duration values (Hours >=0, 0 <= Minutes <= 59).", Status.BAD_REQUEST);
                }
            } else {
                // si hasScale es false
                int tempHScaleProvided, tempMScaleProvided;
                try {
                    //se valida que no pueden estar vacios 
                    if (hoursDurationScale == null || hoursDurationScale.trim().isEmpty() ||
                        minutesDurationScale == null || minutesDurationScale.trim().isEmpty()) {
                        return new Response("If no scale location is selected, scale duration hours and minutes must be explicitly set to '0'. Cannot be empty.", Status.BAD_REQUEST);
                    }
                    tempHScaleProvided = Integer.parseInt(hoursDurationScale.trim());
                    tempMScaleProvided = Integer.parseInt(minutesDurationScale.trim());
                } catch (NumberFormatException e) {
                    return new Response("Scale duration (hours and minutes), if provided without a scale location, must be 0", Status.BAD_REQUEST);
                }

                // Ahora validamos que esos valores parseados sean 0
                if (tempHScaleProvided != 0 || tempMScaleProvided != 0) {
                    return new Response("If no scale location is selected, scale duration hours and minutes must both be 0.", Status.BAD_REQUEST);
                }
            }
        } catch (NumberFormatException e) {

            return new Response("Arrival duration (hours and minutes) must be numeric.", Status.BAD_REQUEST);
        }

        long totalMinutes = hArr * 60L + mArr;
        if (hasScale) { // Solo sumar duración de escala si efectivamente hay una escala.
            totalMinutes += hScale * 60L + mScale;
        }
        if (totalMinutes <= 0) {
            return new Response("Total flight time must be greater than 00:00.", Status.BAD_REQUEST);
        }

        if (!hasScale && (hScale > 0 || mScale > 0)) {
            return new Response("Scale time must be 0 if there is no scale location.", Status.BAD_REQUEST);
        }

        LocalDateTime departureDate;
        try {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d'T'H:m");
            departureDate = LocalDateTime.parse(departureDateStr.trim() + "T" + departureTimeStr.trim(), formatter);

            if (departureDate.isBefore(LocalDateTime.now())) {
                return new Response("Departure date must be in the future or current time.", Status.BAD_REQUEST);
            }
        } catch (DateTimeParseException e) {
            return new Response("Invalid Date or Time format. Use YYYY-MM-DD and HH:mm.", Status.BAD_REQUEST);
        }


        if (hArr < 0 || mArr < 0 || mArr > 59) { 
            return new Response("Invalid arrival duration values (Hours >=0, 0 <= Minutes <= 59).", Status.BAD_REQUEST);
        }
        if (hasScale && (hScale < 0 || mScale < 0 || mScale > 59)) { 
            return new Response("Invalid scale duration values (Hours >=0, 0 <= Minutes <= 59).", Status.BAD_REQUEST);
        }

        FlightRepository flightRepo = FlightRepository.getInstance();
        PlaneRepository planeRepo = PlaneRepository.getInstance();
        LocationRepository locationRepo = LocationRepository.getInstance();

        if (flightRepo.getFlight(id) != null) {
            return new Response("Flight with ID " + id + " already exists.", Status.BAD_REQUEST);
        }

        Plane plane = planeRepo.getPlane(planeId.trim());
        if (plane == null) {
            return new Response("Plane with ID '" + planeId.trim() + "' not found.", Status.NOT_FOUND);
        }

        Location departureLoc = locationRepo.getLocation(departureLocationId.trim());
        Location arrivalLoc = locationRepo.getLocation(arrivalLocationId.trim());
        if (departureLoc == null) {
            return new Response("Departure location with ID '" + departureLocationId.trim() + "' not found.", Status.NOT_FOUND);
        }
        if (arrivalLoc == null) {
            return new Response("Arrival location with ID '" + arrivalLocationId.trim() + "' not found.", Status.NOT_FOUND);
        }

        if (departureLoc.getAirportId().equals(arrivalLoc.getAirportId())) { // Comparar por ID
            return new Response("Departure and arrival locations must be different.", Status.BAD_REQUEST);
        }

        Location scaleLoc = null;
        if (hasScale) {
            scaleLoc = locationRepo.getLocation(scaleLocationId.trim());
            if (scaleLoc == null) {
                return new Response("Scale location with ID '" + scaleLocationId.trim() + "' not found.", Status.NOT_FOUND);
            }
            // Escala no puede ser igual a salida o llegada.
            if (scaleLoc.getAirportId().equals(departureLoc.getAirportId()) || scaleLoc.getAirportId().equals(arrivalLoc.getAirportId())) {
                return new Response("Scale location must be different from departure and arrival.", Status.BAD_REQUEST);
            }
        }

        // CREAR VUELO
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

            return new Response("Failed to save flight. ID might already exist.", Status.INTERNAL_SERVER_ERROR);
        }

    } catch (Exception e) {
        e.printStackTrace(); 
        return new Response("Unexpected error creating flight: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
    }
}

    
    public static Response delayFlight(String flightId, String hoursStr, String minutesStr) {
        if (flightId == null || flightId.trim().isEmpty()) {
            return new Response("Flight ID is required to delay the flight.", Status.BAD_REQUEST);
        }
        int hours;
        int minutes;
           
        try {
       
            hours =  Integer.parseInt(hoursStr.trim());
            minutes = Integer.parseInt(minutesStr.trim());
        } catch (NumberFormatException e) {
            return new Response("Delay hours and minutes must be numeric values.", Status.BAD_REQUEST);
        }

        if (hours < 0 || minutes < 0) {
            return new Response("Delay hours and minutes cannot be negative.", Status.BAD_REQUEST);
        }
        if (hours == 0 && minutes == 0) {
            return new Response("Total delay time (hours and minutes combined) must be greater than 00:00.", Status.BAD_REQUEST);
        }

        try {
            FlightRepository flightRepo = FlightRepository.getInstance();
            Flight flight = flightRepo.getFlight(flightId);
            if (flight == null) { 
                return new Response("Flight with ID '" + flightId + "' not found.", Status.NOT_FOUND);
            }
            boolean success = delayStrategy.applyDelay(flight, hours, minutes);
            if (success) {
                return new Response(
                "Flight " + flightId + " delayed successfully. New departure: " + flight.getDepartureDate(),
                Status.OK,
                flight.clone() 
            );
            } else {
                return new Response("Could not apply delay to flight " + flightId + ".", Status.BAD_REQUEST);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new Response("An unexpected error occurred while delaying the flight: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
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
            passengerRepo.notifyObservers();
            flightRepo.notifyObservers();

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
        return arrivalCalculator.calculate(flight);
    }
}
