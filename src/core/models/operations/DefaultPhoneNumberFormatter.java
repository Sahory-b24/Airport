/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.operations;

import core.models.Passenger;
import core.models.utils.PhoneNumberFormatter;

/**
 *
 * @author ujhuh
 */
public class DefaultPhoneNumberFormatter implements PhoneNumberFormatter{
 @Override
    public String formatPhoneNumber(Passenger passenger) {
        if (passenger == null) {
            return "N/A"; // O manejarlo de otra forma
        }
        // Asumimos que Passenger tiene getCountryPhoneCode() y getPhone()
        return "+" + passenger.getCountryPhoneCode() + " " + passenger.getPhone();
    }
}
