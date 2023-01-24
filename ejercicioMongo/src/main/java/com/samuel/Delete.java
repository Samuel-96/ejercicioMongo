package com.samuel;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Scanner;

public class Delete {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try (MongoClient mc = MongoClients.create("mongodb://localhost/samuel"))
        {
            MongoDatabase md = mc.getDatabase("samuel");
            MongoCollection mco = md.getCollection("videojuegos");

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.print("Introduce el titulo del videojuego que quieras eliminar: ");
            String juego = sc.nextLine();

            md.getCollection("videojuegos").deleteOne(new Document("titulo",juego));
            System.out.println("Videojuego eliminado");

        }

    }
    public void juegoExiste(String juego)
    {

    }

}
