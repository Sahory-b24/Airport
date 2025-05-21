/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Plane;

/**
 *
 * @author Sahory Blanco
 */
public class PlaneController {
    public static Response createPlane(String id, String brand, String model, String maxCapacity, String airline) {
        int capacity;

        // Validación de ID
        if (id == null || !id.matches("[A-Z]{2}\\d{5}")) {
            return new Response("Plane ID must follow format XXYYYYY (2 letters + 5 digits)", Status.BAD_REQUEST);
        }

        // Validación de campos vacíos
        if (brand == null || brand.trim().isEmpty()) {
            return new Response("Brand cannot be empty", Status.BAD_REQUEST);
        }

        if (model == null || model.trim().isEmpty()) {
            return new Response("Model cannot be empty", Status.BAD_REQUEST);
        }

        
        // Validación de capacidad
        if (maxCapacity == null || maxCapacity.trim().isEmpty()) {
            return new Response("Max capacity is required", Status.BAD_REQUEST);
        }

        try {
            capacity = Integer.parseInt(maxCapacity);
            if (capacity <= 0) {
                return new Response("Max capacity must be a positive number", Status.BAD_REQUEST);
            }
        } catch (NumberFormatException e) {
            return new Response("Max capacity must be numeric", Status.BAD_REQUEST);
        }
        if (airline == null || airline.trim().isEmpty()) {
            return new Response("Airline cannot be empty", Status.BAD_REQUEST);
        }

        // Verificación de duplicado
//        PlaneRepository repo = PlaneRepository.getInstance();
//        if (repo.exists(id)) {
//            return new Response("Plane with that ID already exists", Status.BAD_REQUEST);
//        }

        Plane plane = new Plane(id.trim(), brand.trim(), model.trim(), capacity, airline.trim());
//        repo.add(plane);

        return new Response("Plane registered successfully", Status.CREATED);
    }
}
