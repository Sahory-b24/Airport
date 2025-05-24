/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.Passenger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Sahory Blanco
 */
public class PassengerRepository extends ObservableRepository{
    private static PassengerRepository instance;

    private ArrayList<Passenger> passengers;

    private PassengerRepository() {
        this.passengers = new ArrayList<>();
    }

    public static PassengerRepository getInstance() {
        if (instance == null) {
            instance = new PassengerRepository();
        }
        return instance;
    }

    public boolean addPassenger(Passenger p) {
        for (Passenger passenger : this.passengers) {
            if (passenger.getId() == p.getId()) {
                return false;
            }
        }
        this.passengers.add(p);
        notifyObservers();
        return true;
    }

    public Passenger getPassenger(long id) {
        for (Passenger p : passengers) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public boolean deletePassenger(long id) {
        for (Passenger p : passengers) {
            if (p.getId() == id) {
                passengers.remove(p);
                notifyObservers();
                return true;
            }
        }
        return false;
    }

    public void clear() {
        notifyObservers();
        passengers.clear();
    }

    public ArrayList<Passenger> getAllPassengers() {
        ArrayList<Passenger> sortedList = new ArrayList<>();

    // Clonar todos los pasajeros
    for (Passenger p : passengers) {
        sortedList.add(p.clone());
    }

    // Ordenar por ID usando Comparator
    Collections.sort(sortedList, new Comparator<Passenger>() {
        @Override
        public int compare(Passenger p1, Passenger p2) {
           return Long.compare(p1.getId(), p2.getId());        }
    });

    return sortedList;
    }
}
