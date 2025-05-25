/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Location;
import core.models.storage.LocationRepository;

/**
 *
 * @author Sahory Blanco
 */
public class LocationController {
    public static Response createLocation(String id, String name, String city, String country, String latitude, String longitude) {
        double lat, lon;
        // Validaciones
        // Valida que el ID tenga solo letras mayusculas
        if (id == null || !id.matches("[A-Z]{3}")) {
            return new Response("ID must be 3 uppercase letters", Status.BAD_REQUEST);
        }
        // Valida que el nombre no este vacio
        if (name == null || name.trim().isEmpty()) {
            return new Response("Name cannot be empty", Status.BAD_REQUEST);
        }
        //Valica que la ciudad no puede estar vacia
        if (city == null || city.trim().isEmpty()) {
            return new Response("City cannot be empty", Status.BAD_REQUEST);
        }
        //Valida que el pais no puede estar vacio
        if (country == null || country.trim().isEmpty()) {
            return new Response("Country cannot be empty", Status.BAD_REQUEST);
        }
        //Valida las latitudes
        try {
            lat = Double.parseDouble(latitude);
            if (lat < -90 || lat > 90) {
                return new Response("Latitude must be between -90 and 90", Status.BAD_REQUEST);
            }
        } catch (NumberFormatException e) {
            return new Response("Latitude must be numeric", Status.BAD_REQUEST);
        }

        // Validar y convertir longitud
        try {
            lon = Double.parseDouble(longitude);
            if (lon < -180 || lon > 180) {
                return new Response("Longitude must be between -180 and 180", Status.BAD_REQUEST);
            }
        } catch (NumberFormatException e) {
            return new Response("Longitude must be numeric", Status.BAD_REQUEST);
        }

        LocationRepository repo = LocationRepository.getInstance();
        //Valida que no hallan dos locaciones con el mismo ID
        if (repo.getLocation(id) != null) {
            return new Response("Location with this ID already exists", Status.BAD_REQUEST);
        }
        //Indica que la operacion se hizo exitosamente
        Location l = new Location(id, name, city, country, lat, lon);
        repo.addLocation(l);
        return new Response("Location created successfully", Status.CREATED);
    }
}
