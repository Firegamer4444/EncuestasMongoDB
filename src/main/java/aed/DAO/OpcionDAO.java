package aed.DAO;

import aed.MongoDBConnection;
import aed.models.OpcionPropertyBean;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;


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
