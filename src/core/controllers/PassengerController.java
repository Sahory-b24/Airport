/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Passenger;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 *
 * @author Sahory Blanco
 */
public class PassengerController {
    public static Response createPassenger(long id, String firstname, String lastname, LocalDate birthDate, int countryPhoneCode, long phone, String country){
        if (id < 0 || String.valueOf(id).length() > 15) {
            return new Response("ID must be >= 0 and max 15 digits", Status.BAD_REQUEST);
        }

        // Validación de nombre y apellido
        if (firstname == null || firstname.trim().isEmpty()) {
            return new Response("Firstname cannot be empty", Status.BAD_REQUEST);
        }

        if (lastname == null || lastname.trim().isEmpty()) {
            return new Response("Lastname cannot be empty", Status.BAD_REQUEST);
        }

        // Validación de fecha de nacimiento
        if (birthDate == null) {
            return new Response("Birth date is required", Status.BAD_REQUEST);
        }

        // Validación de código de país
        if (countryPhoneCode < 0 || String.valueOf(countryPhoneCode).length() > 3) {
            return new Response("Phone code must be >= 0 and max 3 digits", Status.BAD_REQUEST);
        }

        // Validación del número de teléfono
        if (phone < 0 || String.valueOf(phone).length() > 11) {
            return new Response("Phone number must be >= 0 and max 11 digits", Status.BAD_REQUEST);
        }

        // Validación de país
        if (country == null || country.trim().isEmpty()) {
            return new Response("Country cannot be empty", Status.BAD_REQUEST);
        }

        // Repositorio
//        PassengerRepository repo = PassengerRepository.getInstance();
//        if (repo.exists(id)) {
//            return new Response("A passenger with that ID already exists", Status.BAD_REQUEST);
//        }

        // Crear y guardar
        Passenger p = new Passenger(id, firstname, lastname, birthDate, countryPhoneCode, phone, country);
//        repo.add(p);

        return new Response("Passenger created successfully", Status.CREATED);
    }
}
