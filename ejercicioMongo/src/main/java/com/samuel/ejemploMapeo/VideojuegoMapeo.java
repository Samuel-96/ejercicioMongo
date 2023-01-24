package com.samuel.ejemploMapeo;

import com.samuel.Desarrollador;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.List;

public class VideojuegoMapeo {
    @BsonId
    private ObjectId _id;
    private String titulo;
    private double precio;
    private List<Desarrollador> desarrolladores;

    public VideojuegoMapeo() {
    }

    public VideojuegoMapeo(String titulo, double precio, List<Desarrollador> desarrolladores) {
        this.titulo = titulo;
        this.precio = precio;
        this.desarrolladores = desarrolladores;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public List<Desarrollador> getDesarrolladores() {
        return desarrolladores;
    }

    public void setDesarrolladores(List<Desarrollador> desarrolladores) {
        this.desarrolladores = desarrolladores;
    }

    @Override
    public String toString() {
        return "VideojuegoMapeo{" +
                "_id=" + _id +
                ", titulo='" + titulo + '\'' +
                ", precio=" + precio +
                ", desarrolladores=" + desarrolladores +
                '}';
    }
}
