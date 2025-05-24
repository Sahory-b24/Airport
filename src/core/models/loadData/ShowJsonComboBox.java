/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.loadData;

import core.models.Flight;
import core.models.Location;
import core.models.Passenger;
import core.models.Plane;
import core.models.storage.FlightRepository;
import core.models.storage.LocationRepository;
import core.models.storage.PassengerRepository;
import core.models.storage.PlaneRepository;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author Sahory Blanco
 */
public class ShowJsonComboBox {
    public static void loadPassengers(JComboBox<String> comboBox) {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("Select a user");

        for (Passenger p : PassengerRepository.getInstance().getAllPassengers()) {

            model.addElement(String.valueOf(p.getId()));
        }

        comboBox.setModel(model);
    }
    public static void loadLocations(JComboBox<String> comboBox) {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("Select a location");

        for (Location loc : LocationRepository.getInstance().getAllLocations()) {
            
            model.addElement(loc.getAirportId());
        }

        comboBox.setModel(model);
    }
    public static void loadPlanes(JComboBox<String> comboBox) {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("Select a plane");

        for (Plane plane : PlaneRepository.getInstance().getAllPlanes()) {

            model.addElement(plane.getId());
        }

        comboBox.setModel(model);
    }
    public static void loadFlights(JComboBox<String> comboBox) {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("Select a flight");

        for (Flight flight : FlightRepository.getInstance().getAllFlights()) {
//            String label = flight.getId();
            model.addElement(flight.getId());
        }

        comboBox.setModel(model);
    }
}
