/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.Location;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author Sahory Blanco
 */
public class LocationRepository extends ObservableRepository{
    private static LocationRepository instance;
    private ArrayList<Location> locations;

    private LocationRepository() {
        this.locations = new ArrayList<>();
    }

    public static LocationRepository getInstance() {
        if (instance == null) {
            instance = new LocationRepository();
        }
        return instance;
    }

    public boolean addLocation(Location location) {
        for (Location l : locations) {
            if (l.getAirportId().equals(location.getAirportId())) {
                return false;
            }
        }
        locations.add(location);
        notifyObservers();
        return true;
    }

    public Location getLocation(String id) {
        for (Location l : locations) {
            if (l.getAirportId().equals(id)) {
                return l;
            }
        }
        return null;
    }

    public boolean deleteLocation(String id) {
        for (Location l : locations) {
            if (l.getAirportId().equals(id)) {
                locations.remove(l);
                return true;
            }
        }
        return false;
    }

    public void clear() {
        locations.clear();
    }

    public ArrayList<Location> getAllLocations() {
        ArrayList<Location> copy = new ArrayList<>();
        for (Location l : locations) {
            copy.add(l.clone()); // requiere que Location implemente Cloneable
        }
        return copy;
    }

    public void sortById() {
        locations.sort(Comparator.comparing(Location::getAirportId));
    }

    public ArrayList<Location> getAllLocationsSorted() {
        ArrayList<Location> copy = new ArrayList<>();
        for (Location l : locations) {
            copy.add(l.clone());
        }
        copy.sort(Comparator.comparing(Location::getAirportId));
        return copy;
    }
}
