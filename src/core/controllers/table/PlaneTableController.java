/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.table;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Plane;
import core.models.storage.PlaneRepository;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sahory Blanco
 */
public class PlaneTableController {
    public static Response updatePlanesTable(DefaultTableModel model) {
    try {
        model.setRowCount(0);
        ArrayList<Plane> planes = PlaneRepository.getInstance().getAllPlanesSorted();

        if (planes == null || planes.isEmpty()) {
            return new Response("Plane list is empty.", Status.NO_CONTENT);
        }

        for (Plane p : planes) {
            model.addRow(new Object[]{
                p.getId(),
                p.getBrand(),
                p.getModel(),
                p.getMaxCapacity(),
                p.getAirline()
            });
        }

        return new Response("Plane list updated successfully.", Status.OK);
    } catch (Exception e) {
        return new Response("Unexpected error: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
    }
}
}
