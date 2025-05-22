/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.table;

import core.controllers.PassengerController;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Passenger;
import core.models.storage.PassengerRepository;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sahory Blanco
 */
public class PassengerTableController {
    public static Response updatePassengersTable(DefaultTableModel model) {
        try {
            model.setRowCount(0);
            ArrayList<Passenger> passengers = PassengerRepository.getInstance().getAllPassengers();

            if (passengers == null || passengers.isEmpty()) {
                return new Response("Passenger list is empty.", Status.NO_CONTENT);
            }

            for (Passenger p : passengers) {
                int age = PassengerController.getCalculatedAgeOfPassenger(p);
                String formattedPhone = PassengerController.getFormattedPhoneOfPassenger(p);
                model.addRow(new Object[]{
                    p.getId(),
                    p.getFullname(), // Este método puede quedarse en Passenger.java
                    p.getBirthDate(),
                    age,
                    formattedPhone,
                    p.getCountry(),
                    p.getNumFlights() // Método de Passenger.java
                });
            }

            return new Response("Passenger list updated successfully.", Status.OK);
        } catch (Exception e) {
            return new Response("Unexpected error: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }
}
