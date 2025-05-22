/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.Plane;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Sahory Blanco
 */
public class PlaneRepository {
    private static PlaneRepository instance;
    private ArrayList<Plane> planes;

    private PlaneRepository() {
        this.planes = new ArrayList<>();
    }

    public static PlaneRepository getInstance() {
        if (instance == null) {
            instance = new PlaneRepository();
        }
        return instance;
    }

    public boolean addPlane(Plane plane) {
        for (Plane p : planes) {
            if (p.getId().equals(plane.getId())) {
                return false;
            }
        }
        planes.add(plane);
        return true;
    }

    public Plane getPlane(String id) {
        for (Plane p : planes) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public boolean deletePlane(String id) {
        for (Plane p : planes) {
            if (p.getId().equals(id)) {
                planes.remove(p);
                return true;
            }
        }
        return false;
    }

    public void clear() {
        planes.clear();
    }

    public ArrayList<Plane> getAllPlanes() {
        ArrayList<Plane> copy = new ArrayList<>();
        for (Plane p : planes) {
            copy.add(p.clone()); // necesita que Plane implemente Cloneable
        }
        return copy;
    }

    public void sortById() {
        planes.sort(Comparator.comparing(Plane::getId));
    }

    public ArrayList<Plane> getAllPlanesOrdenados() {
        ArrayList<Plane> copy = new ArrayList<>();
        for (Plane p : planes) {
            copy.add(p.clone());
        }
        copy.sort(Comparator.comparing(Plane::getId));
        return copy;
    }
}
