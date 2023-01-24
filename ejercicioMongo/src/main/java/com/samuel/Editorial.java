package com.samuel;

import org.bson.codecs.pojo.annotations.BsonProperty;

public class Editorial {
    //aqui no hace falta Id ya que solo se pone en el documento raíz de la coleccion
    //en este caso en Libro
    @BsonProperty(value = "nombre_editorial")
    private String nombreEditorial;
    private String direccion;

    public Editorial() {
    }

    public Editorial(String nombreEditorial, String direccion) {
        this.nombreEditorial = nombreEditorial;
        this.direccion = direccion;
    }

    public String getNombreEditorial() {
        return nombreEditorial;
    }

    public void setNombreEditorial(String nombreEditorial) {
        this.nombreEditorial = nombreEditorial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Editorial{" +
                "nombreEditorial='" + nombreEditorial + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}
