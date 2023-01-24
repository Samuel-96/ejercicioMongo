package com.samuel;

import java.util.Date;

public class Videojuego {
    private Long id;
    private String titulo;
    private String desarrolador;
    private double precio;

    public Videojuego() {
    }

    public Videojuego(Long id, String titulo, String desarrolador, double precio) {
        this.id = id;
        this.titulo = titulo;
        this.desarrolador = desarrolador;
        this.precio = precio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDesarrolador() {
        return desarrolador;
    }

    public void setDesarrolador(String desarrolador) {
        this.desarrolador = desarrolador;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Videojuego{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", desarrolador='" + desarrolador + '\'' +
                ", precio=" + precio +
                '}';
    }
}
