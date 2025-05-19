/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;

/**
 *
 * @author Sahory Blanco
 */
public class LocationController {
    public static Response createLocation(String id, String name, String city, String country, double latitude, double longitude) {
        // Validaciones
        if (id == null || !id.matches("[A-Z]{3}")) {
            return new Response("ID must be 3 uppercase letters", Status.BAD_REQUEST);
        }

        if (name == null || name.trim().isEmpty()) {
            return new Response("Name cannot be empty", Status.BAD_REQUEST);
        }

        if (city == null || city.trim().isEmpty()) {
            return new Response("City cannot be empty", Status.BAD_REQUEST);
        }

        if (country == null || country.trim().isEmpty()) {
            return new Response("Country cannot be empty", Status.BAD_REQUEST);
        }

        if (latitude < -90 || latitude > 90) {
            return new Response("Latitude must be between -90 and 90", Status.BAD_REQUEST);
        }

        if (longitude < -180 || longitude > 180) {
            return new Response("Longitude must be between -180 and 180", Status.BAD_REQUEST);
        }

//        LocationRepository repo = LocationRepository.getInstance();
//
//        if (repo.exists(id)) {
//            return new Response("Location with this ID already exists", StatusCode.BAD_REQUEST);
//        }

        //repo.add(new Location(id, name, city, country, latitude, longitude));
        return new Response("Location created successfully", Status.CREATED);
    }
}
