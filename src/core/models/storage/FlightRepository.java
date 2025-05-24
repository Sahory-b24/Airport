/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.Flight;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Sahory Blanco
 */
public class FlightRepository extends ObservableRepository{
    private static FlightRepository instance;
    private ArrayList<Flight> flights;

    private FlightRepository() {
        this.flights = new ArrayList<>();
    }

    public static FlightRepository getInstance() {
        if (instance == null) {
            instance = new FlightRepository();
        }
        return instance;
    }

    public boolean addFlight(Flight flight) {
        for (Flight f : flights) {
            if (f.getId().equals(flight.getId())) {
                return false;
            }
        }
        flights.add(flight);
        notifyObservers();
        return true;
    }

    public Flight getFlight(String id) {
        for (Flight f : flights) {
            if (f.getId().equals(id)) {
                return f;
            }
        }
        return null;
    }

    public boolean deleteFlight(String id) {
        for (Flight f : flights) {
            if (f.getId().equals(id)) {
                flights.remove(f);
                return true;
            }
        }
        return false;
    }

    public void clear() {
        flights.clear();
    }

    public ArrayList<Flight> getAllFlights() {
        ArrayList<Flight> copy = new ArrayList<>();
        for (Flight f : flights) {
            copy.add(f.clone()); // requiere que Flight implemente Cloneable
        }
        return copy;
    }

    public void sortById() {
        flights.sort(Comparator.comparing(Flight::getId));
    }

    public ArrayList<Flight> getAllFlightsSorted() {
     ArrayList<Flight> copy = new ArrayList<>();
    for (Flight f : this.flights) {
        copy.add(f.clone());
    }
    Collections.sort(copy, Comparator.comparing(Flight::getDepartureDate));
    return copy;
}
}