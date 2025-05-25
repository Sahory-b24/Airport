/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.models.utils;

import core.models.Flight;
import java.time.LocalDateTime;

/**
 *
 * @author ujhuh
 */
public interface FlightArrivalCalculator {
     //Interfaz que contiene el metodo calculate
     LocalDateTime calculate(Flight flight);
}
