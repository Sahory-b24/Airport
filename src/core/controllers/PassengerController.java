/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Passenger;
import core.models.storage.PassengerRepository;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 *
 * @author Sahory Blanco
 */
public class PassengerController {
    public static Response createPassenger(String id,String firstname,String lastname,String birthDate,String countryPhoneCode,String phone,String country) {
        long idLong;
            int phoneCodeInt;
            long phoneLong;
            LocalDate parsedBirthDate;
        try{
            // ID
            if (id == null || id.isEmpty()) {
                return new Response("ID cannot be empty", Status.BAD_REQUEST);
            }
            try {
                idLong = Long.parseLong(id);
                if (idLong <= 0 || id.length() > 15) {
                    return new Response("ID must be > 0 and max 15 digits", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                return new Response("ID must be numeric", Status.BAD_REQUEST);
            }

            // Nombre
            if (firstname == null || firstname.trim().isEmpty()) {
                return new Response("Firstname cannot be empty", Status.BAD_REQUEST);
            }

            if (lastname == null || lastname.trim().isEmpty()) {
                return new Response("Lastname cannot be empty", Status.BAD_REQUEST);
            }

            // Fecha de nacimiento
            if (birthDate == null || birthDate.trim().isEmpty()) {
                return new Response("Birth date is required", Status.BAD_REQUEST);
            }
            try {
                parsedBirthDate = LocalDate.parse(birthDate);  // espera "YYYY-MM-DD"
            } catch (DateTimeParseException e) {
                return new Response("Invalid birth date. Use format YYYY-MM-DD", Status.BAD_REQUEST);
            }

            // Código de país
            if (countryPhoneCode == null || countryPhoneCode.isEmpty()) {
                return new Response("Phone code is required", Status.BAD_REQUEST);
            }
            try {
                phoneCodeInt = Integer.parseInt(countryPhoneCode);
                if (phoneCodeInt < 0 || countryPhoneCode.length() > 3) {
                    return new Response("Phone code must be >= 0 and max 3 digits", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                return new Response("Phone code must be numeric", Status.BAD_REQUEST);
            }

            // Teléfono
            if (phone == null || phone.isEmpty()) {
                return new Response("Phone number is required", Status.BAD_REQUEST);
            }
            try {
                phoneLong = Long.parseLong(phone);
                if (phoneLong < 0 || phone.length() > 11) {
                    return new Response("Phone number must be >= 0 and max 11 digits", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                return new Response("Phone must be numeric", Status.BAD_REQUEST);
            }

            // País
            if (country == null || country.trim().isEmpty()) {
                return new Response("Country cannot be empty", Status.BAD_REQUEST);
            }

             //Verificar duplicado
            PassengerRepository repo = PassengerRepository.getInstance();
            if (repo.getPassenger(idLong) != null) {
                return new Response("A passenger with that ID already exists", Status.BAD_REQUEST);
            }

            // Crear y guardar pasajero
            Passenger p = new Passenger(idLong, firstname.trim(), lastname.trim(), parsedBirthDate, phoneCodeInt, phoneLong, country.trim());
            repo.addPassenger(p);

            return new Response("Passenger created successfully", Status.CREATED);
        }catch (Exception e) {
            return new Response("Unexpected error: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }
}
