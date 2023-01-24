package com.samuel;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * CLASE ENCARGADA DE INSERTAR EN LA BASE DE DATOS VARIOS VIDEOJUEGOS INTRODUCIDOS POR EL USUARIO
 */
public class InsertOne {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        try(MongoClient mc = MongoClients.create("mongodb://localhost/samuel")) {
            MongoDatabase md = mc.getDatabase("samuel");
            MongoCollection mco = md.getCollection("videojuegos");

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            String titulo, desarrollador;
            double precio;
            long id;
            Videojuego v;
            Document d;

            System.out.print("\nIntroduce el id del videojuego: ");
            id = sc.nextLong();
            sc.nextLine();
            System.out.print("Introduce el titulo del videojuego: ");
            titulo = sc.nextLine();
            System.out.print("Introduce el desarrollador del videojuego: ");
            desarrollador = sc.nextLine();
            System.out.print("Introduce el precio del videojuego: ");
            precio = sc.nextDouble();
            v = new Videojuego(id, titulo, desarrollador, precio);
            d = new Document("_id", v.getId()).append("titulo", v.getTitulo()).append("desarrollador", v.getDesarrolador()).append("precio", v.getPrecio());

            try {
                mco.insertOne(d);
                System.out.println("\nInsertado el videojuego en la colección");
            } catch (Exception e) {
                System.out.println("\nERROR AL REALIZAR LA INSERCIÓN POSIBLEMENTE DEBIDO A UNA CLAVE PRINCIPAL DUPLICADA");
            }
        }
    }
}
