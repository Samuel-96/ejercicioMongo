package com.samuel;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static MongoClient mongoClient;
    static MongoDatabase db;
    static MongoCollection mco;
    static Scanner sc;

    public static void main(String[] args) {
        try (MongoClient mc = MongoClients.create("mongodb://localhost/samuel"))
        {
             db = mc.getDatabase("samuel");
             mco = db.getCollection("videojuegos");
             sc = new Scanner(System.in);


            menu();
        }
    }

    public static void menu()
    {
        int eleccion = 1;
        while(eleccion!=0)
        {
            tiempoEspera(1);
            Scanner sc = new Scanner(System.in);
            boolean salir = false;
            System.out.println("\nBienvenido a la base de datos de videojuegos, elige una opción del menú para continuar: ");
            System.out.println("1. Realizar inserción única. \n2. Realizar varias inserciones. \n3. Eliminar un videojuego. \n4. Modificar un videojuego. \n5. Mostrar todos los videojuegos de la colección. \n6. Búsqueda simple de un videojuego. \n7. Búsqueda compleja de un videojuego. ");
            System.out.print("\nElige una opción: "); eleccion = sc.nextInt();

                switch(eleccion)
                {
                    default: System.exit(0);
                    case 1: insertOne(mco); break;
                    case 2: insertMany(mco); break;
                    case 3: delete(mco); break;
                    case 4: update(mco); break;
                    case 5: mostrarVideojuegos(mco); break;
                }
        }
    }

    private static void update(MongoCollection mco) {

        if(hayJuegos(mco)==true)
        {
            mostrarVideojuegos(mco);
            System.out.print("Has elegido la opción de modificar un videojuego existente, introduce el id del videojuego que quieras modificar: "); int id = sc.nextInt(); sc.nextLine();
            System.out.print("¿Qué campo deseas modificar? Introduce 'titulo', 'desarrollador' o 'precio' (sin las comillas) para modificarlo: "); String eleccion = sc.nextLine();
            eleccion.toLowerCase();
            Document filtro = new Document("_id",id);
            Document resultado;
            String tituloActual = "";
            MongoCursor cursor = mco.find(filtro).iterator();
            switch(eleccion)
            {
                case "titulo":
                    if(cursor.hasNext())
                    {
                        resultado = (Document) cursor.next();
                        tituloActual = resultado.getString("titulo");
                    }

                    System.out.println("El título actual del videojuego con id: " + id + " es: " + tituloActual);
                    System.out.print("Introduce el nuevo título: "); String nuevoTitulo = sc.nextLine();
                    mco.updateOne(filtro,Updates.set("titulo",nuevoTitulo));

                    break;

                case "desarrollador":

                    break;

                case "precio": double precio = Integer.parseInt(eleccion);
            }
            System.out.println("Actualización realizada");
            tiempoEspera(1);
        }
        else
        {
            System.out.println("La colección está vacía, saliendo al menú...");
            tiempoEspera(1);
        }

    }

    private static void delete(MongoCollection mco) {
        tiempoEspera(1);
        mostrarVideojuegos(mco);
        System.out.print("Introduce el titulo del videojuego que quieras eliminar: ");
        String juego = sc.nextLine();

        MongoCursor<Document> cursor = mco.find(Filters.eq("titulo",juego)).iterator();

        if (cursor.hasNext()) {
            cursor.close();
            db.getCollection("videojuegos").deleteOne(new Document("titulo",juego));
            System.out.println("Videojuego eliminado de la colección");
        } else {
            cursor.close();
            System.out.println("El videojuego que has introducido no se encuentra en la base de datos");
        }
        tiempoEspera(1);

    }

    //MÉTODO ENCARGADO DE REALIZAR VARIAS INSERCIÓN EN LA BASE DE DATOS
    private static void insertMany(MongoCollection mco) {
        tiempoEspera(1);

        List<Document> documentos = new ArrayList<>();
        System.out.print("¿Cuántos videojuegos quieres introducir en la base de datos?: ");
        int numEntradas = sc.nextInt();
        String titulo, desarrollador; double precio; long id;
        Videojuego v;
        Document d;

        for (int i = 0; i<numEntradas; i++)
        {
            System.out.print("\nIntroduce el id del videojuego: "); id = sc.nextLong(); sc.nextLine();
            System.out.print("Introduce el titulo del videojuego: "); titulo = sc.nextLine();
            System.out.print("Introduce el desarrollador del videojuego: "); desarrollador = sc.nextLine();
            System.out.print("Introduce el precio del videojuego: "); precio = sc.nextDouble(); sc.nextLine();
            v = new Videojuego(id,titulo,desarrollador,precio);
            d = new Document("_id",v.getId()).append("titulo",v.getTitulo()).append("desarrollador",v.getDesarrolador()).append("precio",v.getPrecio());
            documentos.add(d);
        }
        try{
            mco.insertMany(documentos);
            System.out.println("\nInsertados " + numEntradas + " videojuegos en la colección");
        }
        catch(Exception e)
        {
            System.out.println("\nERROR AL REALIZAR LA INSERCIÓN POSIBLEMENTE DEBIDO A UNA CLAVE PRINCIPAL DUPLICADA");
        }
        tiempoEspera(1);
    }

    //MÉTODO ENCARGADO DE REALIZAR UNA INSERCIÓN EN LA BASE DE DATOS
    public static void insertOne(MongoCollection mco)
    {
        tiempoEspera(1);
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
        tiempoEspera(1);
    }

    //MÉTODO ENCARGADO DE DETENER LA EJECUCION DEL PROGRAMA DURANTE 1 SEGUNDO PARA EVITAR QUE SE SOLAPE LA INFORMACIÓN DEL PROGRAMA EN EL TERMINAL CON LA INFORMACIÓN DE MONGO
    public static void tiempoEspera(int segundos)
    {
        try {
            Thread.sleep(segundos*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void mostrarVideojuegos(MongoCollection mco)
    {
        if(hayJuegos(mco))
        {
            MongoCollection<Document> coleccion = db.getCollection("videojuegos");
            System.out.println("Los videojuegos que hay en la colección actual son los siguientes: \n");
            for(Document doc : coleccion.find())
            {
                System.out.println("id: " + doc.getLong("_id") + ", titulo: " + doc.getString("titulo") + ", desarrollador: " + doc.getString("desarrollador") + ", precio: " + doc.getDouble("precio"));
                System.out.println("-----------------------------------------------------------------");
            }
            tiempoEspera(3);
        }
        else
        {
            System.out.println("No hay videojuegos en la colección, saliendo al menú...");
            tiempoEspera(1);
        }

    }

    public static boolean hayJuegos(MongoCollection mco)
    {
        MongoCursor cursor = mco.find().iterator();
        if(!cursor.hasNext())
        {
            return false;
        }
        return true;
    }
}
