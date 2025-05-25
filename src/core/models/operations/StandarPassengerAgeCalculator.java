/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.operations;

import core.models.Passenger;
import core.models.utils.PassengerAgeCalculator;
import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author ujhuh
 */
public class StandarPassengerAgeCalculator implements PassengerAgeCalculator {
    // Metodo que implementa una interfaz con el fin de Calcular la edad de un passenger
    @Override
    public int calculateAge(Passenger passenger) {
        if (passenger == null || passenger.getBirthDate() == null) {
            return 0;
        }
        return Period.between(passenger.getBirthDate(), LocalDate.now()).getYears();
    }
}
