package aed.DAO;

import aed.MongoDBConnection;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Arrays;

public class EncuestaDAO {
    private final MongoCollection<Document> collection;

    public EncuestaDAO() {
        MongoDatabase database = MongoDBConnection.getDatabase();
        this.collection = database.getCollection("encuestas");
    }

    public void obtenerEncuestas() {
        FindIterable<Document> encuestas = collection.find();
        for (Document encuesta : encuestas) {
            System.out.println(encuesta.toJson());
        }
    }

    public void insertarEncuesta(String titulo, String descripcion, String... preguntasIds) {
        Document encuesta = new Document("titulo", titulo)
                .append("descripcion", descripcion)
                .append("preguntasIds", Arrays.asList(preguntasIds));

        collection.insertOne(encuesta);
        System.out.println("Encuesta insertada: " + titulo);
    }

}