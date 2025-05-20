/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

/**
 *
 * @author ujhuh
 */
public class AddPassengers {
    private Flight flight;

    public AddPassengers(Flight flight) {
        this.flight = flight;
    }
    
    public void addPassenger(Passenger passenger) {
        flight.passengers.add(passenger);
    }
}
