package aed.dao;

import aed.MongoDBConnection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;


public class OpcionDAO {

    private final MongoCollection<Document> collection;

    public OpcionDAO() {
        MongoDatabase database = MongoDBConnection.getDatabase();
        this.collection = database.getCollection("opciones");
    }

    public void insertarOpcion(String tittuloOpcion) {
        Document opcion = new Document("tittuloOpcion", tittuloOpcion);
        collection.insertOne(opcion);
    }

}
