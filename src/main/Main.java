/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import core.views.AirportFrame;
import com.formdev.flatlaf.FlatDarkLaf;
import core.models.loadData.LoadDataJson;
import javax.swing.UIManager;

/**
 *
 * @author ujhuh
 */
public class Main {
    public static void main(String args[]) {
          // Cargar todos los datos de los JSON
    LoadDataJson dataLoader = new LoadDataJson();
    dataLoader.loadAllData();
    //mostrar interfaz
        System.setProperty("flatlaf.useNativeLibrary", "false");

        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AirportFrame().setVisible(true);
            }
        });
    }
}
