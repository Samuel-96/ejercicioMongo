package com.samuel;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class Desarrollador {
    //el id sólo hace falta ponerlo en la clase raíz, en este caso en la clase VideojuegoMapeo
    private String nombre;
    @BsonProperty(value = "juegos_desarrollados")
    private int juegosDesarrollados;
    @BsonProperty(value = "numero_empleados")
    private int numEmpleados;

    public Desarrollador() {
    }

    public Desarrollador(String nombre, int juegosDesarrollados, int numEmpleados) {
        this.nombre = nombre;
        this.juegosDesarrollados = juegosDesarrollados;
        this.numEmpleados = numEmpleados;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getJuegosDesarrollados() {
        return juegosDesarrollados;
    }

    public void setJuegosDesarrollados(int juegosDesarrollados) {
        this.juegosDesarrollados = juegosDesarrollados;
    }

    public int getNumEmpleados() {
        return numEmpleados;
    }

    public void setNumEmpleados(int numEmpleados) {
        this.numEmpleados = numEmpleados;
    }

    @Override
    public String toString() {
        return "Desarrollador{" +
                "nombre='" + nombre + '\'' +
                ", juegosDesarrollados=" + juegosDesarrollados +
                ", numEmpleados=" + numEmpleados +
                '}';
    }
}
