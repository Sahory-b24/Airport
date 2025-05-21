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
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author Sahory Blanco
 */
public class FlightController {
 // Instantiate strategies directly. In a larger system, they might be injected.
    private static final FlightArrivalCalculator arrivalCalculator = new StandardFlightArrivalCalculator();
    private static final FlightDelay delayStrategy = new SimpleFlightDelay();
    private static final Pattern FLIGHT_ID_PATTERN = Pattern.compile("[A-Z]{3}\\d{3}");


    public static Response createFlight(String id, String planeId, String departureLocationId,
                                        String arrivalLocationId, String scaleLocationId, // Can be null or empty
                                        String departureDateStr, int hoursDurationArrival, int minutesDurationArrival,
                                        int hoursDurationScale, int minutesDurationScale) {

        if (id == null || !FLIGHT_ID_PATTERN.matcher(id).matches()) {
            return new Response("Invalid flight ID. Must be in the format XXXYYY (3 uppercase letters followed by 3 digits).", Status.BAD_REQUEST);
        }
        if (planeId == null || planeId.trim().isEmpty()) {
            return new Response("Plane ID is required.", Status.BAD_REQUEST);
        }
        if (departureLocationId == null || departureLocationId.trim().isEmpty() ||
            arrivalLocationId == null || arrivalLocationId.trim().isEmpty()) {
            return new Response("Departure and arrival locations are required.", Status.BAD_REQUEST);
        }
        if (departureDateStr == null || departureDateStr.trim().isEmpty()) {
            return new Response("Departure date is required.", Status.BAD_REQUEST);
        }
        boolean hasScale = scaleLocationId != null && !scaleLocationId.trim().isEmpty();
        long totalMinutesDuration = (hoursDurationArrival * 60L) + minutesDurationArrival;
        if (hasScale) {
            totalMinutesDuration += (hoursDurationScale * 60L) + minutesDurationScale;
        }
        if (totalMinutesDuration <= 0) {
            return new Response("Total flight time must be greater than 00:00.", Status.BAD_REQUEST);
        }
        if (!hasScale && (hoursDurationScale != 0 || minutesDurationScale != 0)) {
            return new Response("If there is no scale, the scale duration time must be 0.", Status.BAD_REQUEST);
        }

        LocalDateTime departureDate;
        try {
            departureDate = LocalDateTime.parse(departureDateStr);
        } catch (DateTimeParseException e) {
            return new Response("Invalid departure date format. Use yyyy-MM-ddTHH:MM:SS.", Status.BAD_REQUEST);
        }

        FlightRepository flightRepo = FlightRepository.getInstance();
        PlaneRepository planeRepo = PlaneRepository.getInstance();
        LocationRepository locationRepo = LocationRepository.getInstance();

        
        if (flightRepo.getFlight(id) != null) {
            return new Response("A flight with ID: " + id + " already exists.", Status.BAD_REQUEST);
        }
        Plane plane = planeRepo.getPlane(planeId);
        if (plane == null) {
            return new Response("Plane with ID " + planeId + " not found.", Status.NOT_FOUND);
        }

        Location departureLoc = locationRepo.getLocation(departureLocationId);
        if (departureLoc == null) {
            return new Response("Departure location with ID " + departureLocationId + " not found.", Status.NOT_FOUND);
        }

        Location arrivalLoc = locationRepo.getLocation(arrivalLocationId);
        if (arrivalLoc == null) {
            return new Response("Arrival location with ID " + arrivalLocationId + " not found.", Status.NOT_FOUND);
        }

        Location scaleLoc = null;
        if (hasScale) {
            scaleLoc = locationRepo.getLocation(scaleLocationId);
            if (scaleLoc == null) {
                return new Response("Scale location with ID " + scaleLocationId + " not found.", Status.NOT_FOUND);
            }
        }
        
        if (departureLoc.equals(arrivalLoc)) {
            return new Response("Departure location cannot be the same as arrival location.", Status.BAD_REQUEST);
        }
        if (hasScale && (scaleLoc.equals(departureLoc) || scaleLoc.equals(arrivalLoc))) {
            return new Response("Scale location cannot be the same as departure or arrival location.", Status.BAD_REQUEST);
        }


        Flight newFlight;
        if (hasScale) {
            newFlight = new Flight(id, plane, departureLoc, scaleLoc, arrivalLoc, departureDate,
                                   hoursDurationArrival, minutesDurationArrival, hoursDurationScale, minutesDurationScale);
        } else {
            newFlight = new Flight(id, plane, departureLoc, arrivalLoc, departureDate,
                                   hoursDurationArrival, minutesDurationArrival);
        }

        if (flightRepo.addFlight(newFlight)) {
            return new Response("Flight created successfully.", Status.CREATED, newFlight.clone()); //
        } else {
            return new Response("Error saving flight. ID might already exist.", Status.INTERNAL_SERVER_ERROR);
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

   
    public static Response addPassengerToFlight(long passengerId, String flightId) {
        if (flightId == null || flightId.trim().isEmpty()) {
            return new Response("Flight ID is required.", Status.BAD_REQUEST);
        }

        FlightRepository flightRepo = FlightRepository.getInstance();
        PassengerRepository passengerRepo = PassengerRepository.getInstance();

        Flight flight = flightRepo.getFlight(flightId);
        if (flight == null) {
            return new Response("Flight with ID " + flightId + " not found.", Status.NOT_FOUND); //
        }

        Passenger passenger = passengerRepo.getPassenger(passengerId);
        if (passenger == null) {
            return new Response("Passenger with ID " + passengerId + " not found.", Status.NOT_FOUND);
        }

        if (flight.getNumPassengers() >= flight.getPlane().getMaxCapacity()) {
            return new Response("Flight " + flightId + " has reached its maximum passenger capacity.", Status.BAD_REQUEST);
        }
        flight.addPassenger(passenger); 
        passenger.addFlight(flight);    

        return new Response("Passenger " + passengerId + " added to flight " + flightId + " successfully.", Status.OK, flight.clone()); //
    }


    public static Response getAllFlightsSortedByDate() {
        FlightRepository flightRepo = FlightRepository.getInstance();
        List<Flight> flights = flightRepo.getAllFlights(); 
        List<Flight> flightClones = new ArrayList<>();
        for (Flight f : flights) {
            flightClones.add(f.clone()); //
        }
        return new Response("Flights retrieved successfully.", Status.OK, flightClones);
    }
    public static Response getFlightsForPassenger(long passengerId) {
        PassengerRepository passengerRepo = PassengerRepository.getInstance();
        Passenger passenger = passengerRepo.getPassenger(passengerId);

        if (passenger == null) {
            return new Response("Passenger with ID " + passengerId + " not found.", Status.NOT_FOUND);
        }

        ArrayList<Flight> flights = new ArrayList<>(passenger.getFlights()); 
        flights.sort((f1, f2) -> f1.getDepartureDate().compareTo(f2.getDepartureDate()));

        List<Flight> flightClones = new ArrayList<>();
        for (Flight f : flights) {
            flightClones.add(f.clone()); //
        }
        return new Response("Flights for passenger " + passengerId + " retrieved.", Status.OK, flightClones);
    }
    public static LocalDateTime getCalculatedArrivalDateForFlight(Flight flight) {
        if (flight == null) {
            return null;
        }
        return arrivalCalculator.calculate(flight);
    }
}
