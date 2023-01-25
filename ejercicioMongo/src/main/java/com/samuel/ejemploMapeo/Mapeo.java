package com.samuel.ejemploMapeo;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import com.samuel.Desarrollador;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.ArrayList;
import java.util.List;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class Mapeo {
    public static void main(String[] args) {

        //
        ConnectionString connectionString = new ConnectionString("mongodb://localhost/samuel");
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                pojoCodecRegistry);
        MongoClientSettings clientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .codecRegistry(codecRegistry)
                .build();

        try(MongoClient mongoClient = MongoClients.create(clientSettings))
        {
            MongoDatabase db = mongoClient.getDatabase("samuel");
            MongoCollection<VideojuegoMapeo> videojuegos = db.getCollection("videojuegosMapeados",VideojuegoMapeo.class);
            MongoCursor<VideojuegoMapeo> mc = videojuegos.find().iterator();
            while(mc.hasNext())
                System.out.println(mc.next());
            Desarrollador d1 = new Desarrollador("Bandai Namco",12,150);
            Desarrollador d2 = new Desarrollador("Square Enix",18,500);
            List<Desarrollador> desarrolladores = new ArrayList<>();
            desarrolladores.add(d1); desarrolladores.add(d2);
            VideojuegoMapeo videojuegoMapeo1 = new VideojuegoMapeo("Xenogears",39.90,desarrolladores);
            VideojuegoMapeo videojuegoMapeo2 = new VideojuegoMapeo("Jet Set Radio",39.90,desarrolladores);

            videojuegos.insertOne(videojuegoMapeo1);
            videojuegos.insertOne(videojuegoMapeo2);
            System.out.println("Inserción realizada");
        }
        catch(Exception e)
        {
            System.out.println("Inserción no realizada");
        }
    }
}
