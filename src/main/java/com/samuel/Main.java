package com.samuel;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
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
            sc = new Scanner(System.in);

            System.out.println("\nBienvenido a la base de datos de videojuegos, elige una opción del menú para continuar: ");
            System.out.println("1. Realizar inserción única. \n2. Realizar varias inserciones. \n3. Eliminar un videojuego. \n4. Modificar un videojuego. \n5. Mostrar todos los videojuegos de la colección. \n6. Cargar datos de prueba. \n7. Búsqueda simple de un videojuego. \n8. Búsqueda compleja de un videojuego. \n9. Eliminar todos los videojuegos de la colección. ");
            System.out.print("\nElige una opción: "); eleccion = sc.nextInt(); sc.nextLine();

                switch(eleccion)
                {
                    default: System.exit(0);
                    case 1: insertOne(mco); break;
                    case 2: insertMany(mco); break;
                    case 3: delete(mco); break;
                    case 4: update(mco); break;
                    case 5: mostrarVideojuegos(mco); break;
                    case 6: cargarDatosDePrueba(mco); break;
                    case 7: busquedaSimple(mco); break;
                    case 8: busquedaCompleja(mco); break;
                    case 9: eliminarColeccion(mco); break;
                }
        }
    }

    private static void busquedaCompleja(MongoCollection mco) {
        if(hayJuegos(mco))
        {
            tiempoEspera(1);
            System.out.print("¿Quieres buscar por id, título, desarrollador o precio? (introduce 'id','titulo','precio','desarrollador' sin comillas simples: ");
            String eleccion = sc.nextLine();
            eleccion = eleccion.toLowerCase();
            //compruebo si se ha introducido la palabra correcta
            if(eleccion.equals("id") || eleccion.equals("titulo") || eleccion.equals("desarrollador") || eleccion.equals("precio"))
            {
                MongoCursor cursor;
                Document d;
                switch(eleccion)
                {
                    case "id":
                        System.out.print("Introduce el id del videojuego/videojuegos que quieras buscar: "); long id = sc.nextLong(); sc.nextLine();
                        cursor = mco.find(new Document("_id",id)).iterator();
                        if(!cursor.hasNext())
                        {
                            System.out.println("No se encuentra un videojuego con ese id");
                        }
                        while(cursor.hasNext())
                        {
                            d = (Document) cursor.next();
                            System.out.println("El videojuego con id " + id + " es " + d.getString("titulo") + ", desarrollado por " + d.getString("desarrollador") + " y cuesta " + d.getDouble( "precio") + " euros.");
                        }
                        break;

                    case "titulo":
                        System.out.print("Introduce el titulo del videojuego/videojuegos que quieras buscar: "); String titulo = sc.nextLine();
                        cursor = mco.find(new Document("titulo",titulo)).iterator();
                        if(!cursor.hasNext())
                        {
                            System.out.println("No se encuentra un videojuego con ese título");
                        }
                        while(cursor.hasNext())
                        {
                            d = (Document) cursor.next();
                            System.out.println("El videojuego con id " + d.getLong("_id") + " es " + titulo + ", desarrollado por " + d.getString("desarrollador") + " y cuesta " + d.getDouble( "precio") + " euros.");
                        }

                        break;

                    case "desarrollador":

                        System.out.print("Introduce el desarrollador del videojuego/videojuegos que quieras buscar: "); String desarrollador = sc.nextLine();
                        cursor = mco.find(new Document("desarrollador",desarrollador)).iterator();
                        if(!cursor.hasNext())
                        {
                            System.out.println("No se encuentra un videojuego con ese desarrollador");
                        }
                        while(cursor.hasNext())
                        {
                            d = (Document) cursor.next();
                            System.out.println("El videojuego con id " + d.getLong("_id") + " es " + d.getString("titulo") + ", desarrollado por " + desarrollador + " y cuesta " + d.getDouble( "precio") + " euros.");
                        }


                        break;

                    case "precio":
                        System.out.print("Introduce el precio del videojuego/videojuegos que quieras buscar: "); double precio = sc.nextDouble(); sc.nextLine();
                        cursor = mco.find(new Document("precio",precio)).iterator();
                        if(!cursor.hasNext())
                        {
                            System.out.println("No se encuentra un videojuego con ese precio");
                        }
                        while(cursor.hasNext())
                        {
                            d = (Document) cursor.next();
                            System.out.println("El videojuego con id " + d.getLong("_id") + " es " + d.getString("titulo") + ", desarrollado por " + d.getString("desarrollador") + " y cuesta " + precio + " euros.");
                        }


                }
                tiempoEspera(2);
            }
            else
            {
                System.out.println("No has introducido 'id', 'titulo', 'desarrollador' o 'precio'. Saliendo al menú...");
            }

        }
        else
        {
            System.out.println("No hay juegos en la colección, saliendo al menú...");
        }
    }

    private static void busquedaSimple(MongoCollection mco) {
        if(hayJuegos(mco))
        {
            tiempoEspera(1);
            System.out.print("¿Quieres buscar por id, título, desarrollador o precio? (introduce 'id','titulo','precio','desarrollador' sin comillas simples: ");
            String eleccion = sc.nextLine();
            eleccion = eleccion.toLowerCase();
            //compruebo si se ha introducido la palabra correcta
            if(eleccion.equals("id") || eleccion.equals("titulo") || eleccion.equals("desarrollador") || eleccion.equals("precio"))
            {
                MongoCursor cursor;
                Document d;
                switch(eleccion)
                {
                    case "id":
                                System.out.print("Introduce el id del videojuego/videojuegos que quieras buscar: "); long id = sc.nextLong(); sc.nextLine();
                                cursor = mco.find(new Document("_id",id)).iterator();
                                if(!cursor.hasNext())
                                {
                                    System.out.println("No se encuentra un videojuego con ese id");
                                }
                                while(cursor.hasNext())
                                {
                                    d = (Document) cursor.next();
                                    System.out.println("El videojuego con id " + id + " es " + d.getString("titulo") + ", desarrollado por " + d.getString("desarrollador") + " y cuesta " + d.getDouble( "precio") + " euros.");
                                }
                        break;

                    case "titulo":
                                System.out.print("Introduce el titulo del videojuego/videojuegos que quieras buscar: "); String titulo = sc.nextLine();
                                cursor = mco.find(new Document("titulo",titulo)).iterator();
                                if(!cursor.hasNext())
                                {
                                    System.out.println("No se encuentra un videojuego con ese título");
                                }
                                while(cursor.hasNext())
                                {
                                    d = (Document) cursor.next();
                                    System.out.println("El videojuego con id " + d.getLong("_id") + " es " + titulo + ", desarrollado por " + d.getString("desarrollador") + " y cuesta " + d.getDouble( "precio") + " euros.");
                                }

                        break;

                    case "desarrollador":

                        System.out.print("Introduce el desarrollador del videojuego/videojuegos que quieras buscar: "); String desarrollador = sc.nextLine();
                        cursor = mco.find(new Document("desarrollador",desarrollador)).iterator();
                        if(!cursor.hasNext())
                        {
                            System.out.println("No se encuentra un videojuego con ese desarrollador");
                        }
                        while(cursor.hasNext())
                        {
                            d = (Document) cursor.next();
                            System.out.println("El videojuego con id " + d.getLong("_id") + " es " + d.getString("titulo") + ", desarrollado por " + desarrollador + " y cuesta " + d.getDouble( "precio") + " euros.");
                        }


                        break;

                    case "precio":
                        System.out.print("Introduce el precio del videojuego/videojuegos que quieras buscar: "); double precio = sc.nextDouble(); sc.nextLine();
                        cursor = mco.find(new Document("precio",precio)).iterator();
                        if(!cursor.hasNext())
                        {
                            System.out.println("No se encuentra un videojuego con ese precio");
                        }
                        while(cursor.hasNext())
                        {
                            d = (Document) cursor.next();
                            System.out.println("El videojuego con id " + d.getLong("_id") + " es " + d.getString("titulo") + ", desarrollado por " + d.getString("desarrollador") + " y cuesta " + precio + " euros.");
                        }


                }
                tiempoEspera(2);
            }
            else
            {
                System.out.println("No has introducido 'id', 'titulo', 'desarrollador' o 'precio'. Saliendo al menú...");
            }

        }
        else
        {
            System.out.println("No hay juegos en la colección, saliendo al menú...");
        }
    }

    private static void eliminarColeccion(MongoCollection mco) {
        if(hayJuegos(mco))
        {
            //el método deleteMany recibe un argumento que es el filtro, al pasarle un filtro vacío selecciona todos los documentos de la colección y los elimina
            DeleteResult result = mco.deleteMany(new Document());
            System.out.println("Videojuegos eliminados: " + result.getDeletedCount());
        }
        else
        {
            System.out.println("No hay videojuegos en la colección");

        }
        tiempoEspera(2);
    }

    private static void cargarDatosDePrueba(MongoCollection mco) {
        if(!hayJuegos(mco))
        {
            List<Document> documentos = new ArrayList<>();
            Document d1 = new Document("_id",1L).append("titulo","Final Fantasy X").append("desarrollador","Square Enix").append("precio",20.90);
            Document d2 = new Document("_id",2L).append("titulo","Gran Turismo").append("desarrollador","Polyphony Digital").append("precio",30.95);
            Document d3 = new Document("_id",3L).append("titulo","Super Mario 64").append("desarrollador","Nintendo").append("precio",59.95);
            Document d4 = new Document("_id",4L).append("titulo","Pokémon Platino").append("desarrollador","Game Freak").append("precio",39.95);
            Document d5 = new Document("_id",5L).append("titulo","Assassin's Creed").append("desarrollador","Ubisoft").append("precio",9.95);
            Document d6 = new Document("_id",6L).append("titulo","Genshin Impact").append("desarrollador","MihoYo").append("precio",4.95);
            Document d7 = new Document("_id",7L).append("titulo","Trails to Azure").append("desarrollador","Nihon Falcom").append("precio",49.95);
            Document d8 = new Document("_id",8L).append("titulo","Kingdom Hearts").append("desarrollador","Square Enix").append("precio",15.95);
            Document d9 = new Document("_id",9L).append("titulo","Shadow of the Colossus").append("desarrollador","Team ICO").append("precio",49.95);
            Document d10 = new Document("_id",10L).append("titulo","The Last Guardian").append("desarrollador","Team ICO").append("precio",59.95);
            Document d11 = new Document("_id",11L).append("titulo","The Legend of Heroes").append("desarrollador","Nihon Falcom").append("precio",34.95);
            Document d12 = new Document("_id",12L).append("titulo","Mario Kart 8").append("desarrollador","Nintendo").append("precio",99.95);
            Document d13 = new Document("_id",13L).append("titulo","Triangle Strategy").append("desarrollador","Square Enix").append("precio",30.95);
            Document d14 = new Document("_id",14L).append("titulo","Dark Souls").append("desarrollador","From Software").append("precio",19.90);
            Document d15 = new Document("_id",15L).append("titulo","Sekiro").append("desarrollador","From Software").append("precio",39.90);
            Document d16 = new Document("_id",16L).append("titulo","Elden Ring").append("desarrollador","From Software").append("precio",19.90);
            documentos.add(d1);documentos.add(d2);documentos.add(d3);documentos.add(d4);documentos.add(d5);documentos.add(d6);documentos.add(d7);documentos.add(d8);documentos.add(d9);documentos.add(d10);documentos.add(d11);documentos.add(d12);documentos.add(d13);documentos.add(d14);documentos.add(d15);documentos.add(d16);
            mco.insertMany(documentos);
            System.out.println("Datos de prueba cargados en la colección videojuegos");
        }
        else
        {
            System.out.println("Lo siento, ya hay videojuegos introducidos en la base de datos, para evitar inconsistencias no se van a cargar los datos de prueba");
        }
        tiempoEspera(2);
    }

    private static void update(MongoCollection mco) {

        //compruebo si hay juegos en la coleccion
        if(hayJuegos(mco))
        {
            mostrarVideojuegos(mco);
            System.out.print("Has elegido la opción de modificar un videojuego existente, introduce el id del videojuego que quieras modificar: "); int id = sc.nextInt();
            Document filtro = new Document("_id",id);
            MongoCursor cursor = mco.find(filtro).iterator();
            //compruebo si el id introducido existe en la coleccion
            if(cursor.hasNext())
            {
                sc.nextLine();
                System.out.print("¿Qué campo deseas modificar? Introduce 'titulo', 'desarrollador' o 'precio' (sin las comillas) para modificarlo: "); String eleccion = sc.nextLine();
                eleccion = eleccion.toLowerCase();
                //compruebo si se ha introducido la palabra correcta
                if(eleccion.equals("titulo") || eleccion.equals("desarrollador") || eleccion.equals("precio"))
                {
                    Document resultado;
                    String tituloActual = "", desarrolladorActual = "";
                    double precioActual = 0;
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
                            if(cursor.hasNext())
                            {
                                resultado = (Document) cursor.next();
                                desarrolladorActual = resultado.getString("desarrollador");
                            }

                            System.out.println("El desarrollador actual del videojuego con id: " + id + " es: " + desarrolladorActual);
                            System.out.print("Introduce el nuevo desarrollador: "); String nuevoDesarrollador = sc.nextLine();
                            mco.updateOne(filtro,Updates.set("desarrollador",nuevoDesarrollador));

                            break;

                        case "precio":
                            if(cursor.hasNext())
                            {
                                resultado = (Document) cursor.next();
                                precioActual = resultado.getDouble("precio");
                            }

                            System.out.println("El precio actual del videojuego con id: " + id + " es: " + precioActual);
                            System.out.print("Introduce el nuevo precio: "); double nuevoPrecio = sc.nextDouble();
                            mco.updateOne(filtro,Updates.set("precio",nuevoPrecio));
                    }
                    System.out.println("Actualización realizada");
                    tiempoEspera(1);
                }
                else
                {
                    System.out.println("No has introducido 'titulo', 'desarrollador' o 'precio'. Saliendo al menú...");
                    tiempoEspera(1);
                }

            }
            else
            {
                System.out.println("No hay videojuegos con ese id en la colección, saliendo al menú...");
                tiempoEspera(1);
            }

        }
        else
        {
            System.out.println("La colección está vacía, saliendo al menú...");
            tiempoEspera(1);
        }

    }

    private static void delete(MongoCollection mco) {
        tiempoEspera(1);
        if(hayJuegos(mco))
        {
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
        else
        {
            System.out.println("La colección está vacía, saliendo al menú...");
            tiempoEspera(1);
        }

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
        tiempoEspera(1);
        if(hayJuegos(mco))
        {
            MongoCollection<Document> coleccion = db.getCollection("videojuegos");
            System.out.println("Los videojuegos que hay en la colección actual son los siguientes: \n");
            for(Document doc : coleccion.find())
            {
                System.out.println("id: " + doc.getLong("_id") + ", titulo: " + doc.getString("titulo") + ", desarrollador: " + doc.getString("desarrollador") + ", precio: " + doc.getDouble("precio"));
                System.out.println("-------------------------------------------------------------------------");
            }
            tiempoEspera(2);
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
