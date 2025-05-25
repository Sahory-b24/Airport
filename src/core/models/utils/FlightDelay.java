/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.models.utils;

import core.models.Flight;

/**
 *
 * @author ujhuh
 */
public interface FlightDelay {
     //Interfaz que contiene el metodo ApplyDelay
     boolean applyDelay(Flight flight, int hours, int minutes);
}

