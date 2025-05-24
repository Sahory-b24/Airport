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
     private static final DateTimeFormatter INPUT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-M-d'T'H:m"); 

    public static Response createFlight(
        String idStr, 
        String planeIdStr,
        String departureLocationIdStr,
        String arrivalLocationIdStr,
        String scaleLocationIdStr,
        String departureDateInputStr,   
        String departureTimeInputStr,   
        String hoursDurationArrivalStr,
        String minutesDurationArrivalStr,
        String hoursDurationScaleStr,
        String minutesDurationScaleStr
    ) {
        try {

            if (idStr == null || idStr.trim().isEmpty()) {
                return new Response("Flight ID is required.", Status.BAD_REQUEST);
            }
            idStr = idStr.trim(); 
            if (!FLIGHT_ID_PATTERN.matcher(idStr).matches()) {
                return new Response("Invalid Flight ID. Must be 3 uppercase letters followed by 3 digits (e.g., ABC123).", Status.BAD_REQUEST);
            }

           
            if (planeIdStr == null || planeIdStr.trim().isEmpty() || planeIdStr.equalsIgnoreCase("Plane")) {
                return new Response("Plane ID is required and cannot be the placeholder 'Plane'.", Status.BAD_REQUEST);
            }
            planeIdStr = planeIdStr.trim();

            if (departureLocationIdStr == null || departureLocationIdStr.trim().isEmpty() || departureLocationIdStr.equalsIgnoreCase("Location")) {
                return new Response("Departure Location ID is required and cannot be the placeholder 'Location'.", Status.BAD_REQUEST);
            }
            departureLocationIdStr = departureLocationIdStr.trim();

            if (arrivalLocationIdStr == null || arrivalLocationIdStr.trim().isEmpty() || arrivalLocationIdStr.equalsIgnoreCase("Location")) {
                return new Response("Arrival Location ID is required and cannot be the placeholder 'Location'.", Status.BAD_REQUEST);
            }
            arrivalLocationIdStr = arrivalLocationIdStr.trim();

          
            if (departureDateInputStr == null || departureDateInputStr.trim().isEmpty() ||
                departureTimeInputStr == null || departureTimeInputStr.trim().isEmpty() ||
                hoursDurationArrivalStr == null || hoursDurationArrivalStr.trim().isEmpty() || hoursDurationArrivalStr.equalsIgnoreCase("Hour") ||
                minutesDurationArrivalStr == null || minutesDurationArrivalStr.trim().isEmpty() || minutesDurationArrivalStr.equalsIgnoreCase("Minute")) {
                return new Response("Departure date, time, and main flight duration (arrival leg) are required fields and cannot be placeholders.", Status.BAD_REQUEST);
            }

            boolean hasScale = !(scaleLocationIdStr == null || scaleLocationIdStr.trim().isEmpty() ||
                                 scaleLocationIdStr.equalsIgnoreCase("Location") || 
                                 scaleLocationIdStr.equalsIgnoreCase("None"));
            
            String finalScaleLocationId = hasScale ? scaleLocationIdStr.trim() : null;

            int hArr, mArr, hScale = 0, mScale = 0;
            try {
                hArr = Integer.parseInt(hoursDurationArrivalStr.trim());
                mArr = Integer.parseInt(minutesDurationArrivalStr.trim());
                if (hArr < 0 || mArr < 0 || mArr > 59) {
                    return new Response("Invalid arrival duration values (Hours >= 0, 0 <= Minutes <= 59).", Status.BAD_REQUEST);
                }
                if (hArr == 0 && mArr == 0 && !hasScale) { 
                    return new Response("Main flight duration (arrival leg) cannot be zero for a non-stop flight.", Status.BAD_REQUEST);
                }

                if (hasScale) {
                    if (hoursDurationScaleStr == null || hoursDurationScaleStr.trim().isEmpty() || hoursDurationScaleStr.equalsIgnoreCase("Hour") ||
                        minutesDurationScaleStr == null || minutesDurationScaleStr.trim().isEmpty() || minutesDurationScaleStr.equalsIgnoreCase("Minute")) {
                        return new Response("Scale duration (hours and minutes) must be provided if a scale location is selected and cannot be placeholders.", Status.BAD_REQUEST);
                    }
                    hScale = Integer.parseInt(hoursDurationScaleStr.trim());
                    mScale = Integer.parseInt(minutesDurationScaleStr.trim());
                    if (hScale < 0 || mScale < 0 || mScale > 59) {
                        return new Response("Invalid scale duration values (Hours >= 0, 0 <= Minutes <= 59).", Status.BAD_REQUEST);
                    }
                } else { 
                    int tempHScale = (hoursDurationScaleStr == null || hoursDurationScaleStr.trim().isEmpty() || hoursDurationScaleStr.equalsIgnoreCase("Hour")) ? 0 : Integer.parseInt(hoursDurationScaleStr.trim());
                    int tempMScale = (minutesDurationScaleStr == null || minutesDurationScaleStr.trim().isEmpty() || minutesDurationScaleStr.equalsIgnoreCase("Minute")) ? 0 : Integer.parseInt(minutesDurationScaleStr.trim());
                    if (tempHScale != 0 || tempMScale != 0) {
                        return new Response("If no scale location is selected, scale duration hours and minutes must both be 0.", Status.BAD_REQUEST);
                    }
                    hScale = 0; 
                    mScale = 0;
                }
            } catch (NumberFormatException e) {
                return new Response("All duration, hour, and minute fields must be valid numeric values.", Status.BAD_REQUEST);
            }

           
            long totalFlightMinutes = (hArr * 60L) + mArr;
            if (hasScale) {
                totalFlightMinutes += (hScale * 60L) + mScale;
            }
            if (totalFlightMinutes <= 0) {
                return new Response("Total flight time must be greater than 00:00.", Status.BAD_REQUEST);
            }

           
            LocalDateTime departureDateTime;
            try {
                String fullDepartureDateTimeStr = departureDateInputStr.trim() + "T" + departureTimeInputStr.trim();
                departureDateTime = LocalDateTime.parse(fullDepartureDateTimeStr, INPUT_DATE_TIME_FORMATTER);

                if (departureDateTime.isBefore(LocalDateTime.now().minusMinutes(1))) {
                    return new Response("Departure date and time must be in the future or very close to current time.", Status.BAD_REQUEST);
                }
            } catch (DateTimeParseException e) {
                return new Response("Invalid Date or Time format. Ensure date is YYYY-M-D and time is H:M. Details: " + e.getMessage(), Status.BAD_REQUEST);
            }
            
            
            FlightRepository flightRepo = FlightRepository.getInstance();
            PlaneRepository planeRepo = PlaneRepository.getInstance();
            LocationRepository locationRepo = LocationRepository.getInstance();

            if (flightRepo.getFlight(idStr) != null) {
                return new Response("Flight with ID '" + idStr + "' already exists.", Status.BAD_REQUEST);
            }

            
            Plane plane = planeRepo.getPlane(planeIdStr);
            if (plane == null) {
                return new Response("Plane with ID '" + planeIdStr + "' not found.", Status.NOT_FOUND);
            }

            
            Location departureLoc = locationRepo.getLocation(departureLocationIdStr);
            Location arrivalLoc = locationRepo.getLocation(arrivalLocationIdStr);
            if (departureLoc == null) {
                return new Response("Departure location with ID '" + departureLocationIdStr + "' not found.", Status.NOT_FOUND);
            }
            if (arrivalLoc == null) {
                return new Response("Arrival location with ID '" + arrivalLocationIdStr + "' not found.", Status.NOT_FOUND);
            }
            if (departureLoc.getAirportId().equals(arrivalLoc.getAirportId())) {
                return new Response("Departure and arrival locations must be different.", Status.BAD_REQUEST);
            }

            Location scaleLoc = null;
            if (hasScale) {
                scaleLoc = locationRepo.getLocation(finalScaleLocationId); 
                if (scaleLoc == null) {
                    return new Response("Scale location with ID '" + finalScaleLocationId + "' not found.", Status.NOT_FOUND);
                }
                if (scaleLoc.getAirportId().equals(departureLoc.getAirportId()) || scaleLoc.getAirportId().equals(arrivalLoc.getAirportId())) {
                    return new Response("Scale location must be different from departure and arrival locations.", Status.BAD_REQUEST);
                }
            }

            Flight newFlight;
            if (hasScale) {
                newFlight = new Flight(idStr, plane, departureLoc, scaleLoc, arrivalLoc, departureDateTime,
                                       hArr, mArr, hScale, mScale);
            } else {
                newFlight = new Flight(idStr, plane, departureLoc, arrivalLoc, departureDateTime,
                                       hArr, mArr);
            }

            if (plane != null) { 
                plane.addFlight(newFlight);
            }
            
            if (flightRepo.addFlight(newFlight)) {
                return new Response("Flight created successfully.", Status.CREATED, newFlight.clone());
            } else {
                return new Response("Failed to save flight. ID might already exist or another internal issue occurred.", Status.INTERNAL_SERVER_ERROR);
            }

        } catch (NumberFormatException e) {
          
            return new Response("One or more duration/hour/minute fields are not valid numbers. Placeholders like 'Hour' are not numbers. Original error: " + e.getMessage(), Status.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("An unexpected error occurred while creating the flight: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
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
