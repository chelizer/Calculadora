package com.example.sena.myapplication;

/**
 * Created by Sena on 24/10/2015.
 */
public class Plazos {

    int id;
    String nombre;
    //Constructor
    public Plazos(int id, String nombre) {
        super();
        this.id = id;
        this.nombre = nombre;
    }
    @Override
    public String toString() {
        return nombre;
    }
    public int getId() {
        return id;
    }
}
