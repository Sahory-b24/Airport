/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.utils.Observer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sahory Blanco
 */
// Clase que se encarga de gestionar el storage de los observer
public abstract class ObservableRepository {
    private final List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer o) {
        observers.add(o);
    }

    public void notifyObservers() {
        for (Observer o : observers) {
            o.update();
        }
    }
}
