/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.table;

import core.models.utils.Observer;

/**
 *
 * @author Sahory Blanco
 */
// Observadores encargados de actualizar las tablas
public class TableObserverController implements Observer {
    private Runnable updateFunction;

    public TableObserverController(Runnable updateFunction) {
        this.updateFunction = updateFunction;
    }

    @Override
    public void update() {
        updateFunction.run();
    }
}
