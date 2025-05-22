/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.table;

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
                model.addRow(new Object[]{
                    p.getId(),
                    p.getFirstname(),
                    p.getLastname(),
                    p.getBirthDate(),
                    "+" + p.getCountryPhoneCode(),
                    p.getPhone(),
                    p.getCountry()
                });
            }

            return new Response("Passenger list updated successfully.", Status.OK);
        } catch (Exception e) {
            return new Response("Unexpected error: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }
}
