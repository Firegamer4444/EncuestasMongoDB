package aed.DAO;

import aed.MongoDBConnection;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.Arrays;

public class AnalisisDAO {
    private final MongoCollection<Document> collection;

    public AnalisisDAO() {
        MongoDatabase database = MongoDBConnection.getDatabase();
        this.collection = database.getCollection("respuestas");
    }

    public void contarRespuestasPorOpcion(String preguntaId) {
        AggregateIterable<Document> resultado = collection.aggregate(Arrays.asList(
                new Document("$unwind", "$respuestas"),
                new Document("$match", new Document("respuestas.preguntaId", preguntaId)),
                new Document("$group", new Document("_id", "$respuestas.respuesta").append("total", new Document("$sum", 1)))
        ));

        for (Document doc : resultado) {
            System.out.println("Opción: " + doc.getString("_id") + " - Total: " + doc.getInteger("total"));
        }
    }
}
