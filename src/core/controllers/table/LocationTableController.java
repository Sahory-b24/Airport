/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.table;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Location;
import core.models.storage.LocationRepository;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sahory Blanco
 */
public class LocationTableController {
    public static Response updateLocationsTable(DefaultTableModel model) {
        try {
            model.setRowCount(0);
            ArrayList<Location> locations = LocationRepository.getInstance().getAllLocations();

            if (locations == null || locations.isEmpty()) {
                return new Response("Location list is empty.", Status.NO_CONTENT);
            }

            for (Location l : locations) {
                model.addRow(new Object[]{
                    l.getAirportId(),
                    l.getAirportName(),
                    l.getAirportCity(),
                    l.getAirportCountry(),
                    l.getAirportLatitude(),
                    l.getAirportLongitude()
                });
            }

            return new Response("Location list updated successfully.", Status.OK);
        } catch (Exception e) {
            return new Response("Unexpected error: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }
}
