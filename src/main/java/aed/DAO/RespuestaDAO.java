package aed.DAO;

import aed.MongoDBConnection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.Arrays;

public class RespuestaDAO {
    private final MongoCollection<Document> collection;

    public RespuestaDAO() {
        MongoDatabase database = MongoDBConnection.getDatabase();
        this.collection = database.getCollection("respuestas");
    }

    public void insertarRespuesta(String encuestaId, String usuarioId, String preguntaId, String respuesta) {
        Document respuestaDoc = new Document("encuestaId", encuestaId)
                .append("usuarioId", usuarioId)
                .append("respuestas", Arrays.asList(
                        new Document("preguntaId", preguntaId).append("respuesta", respuesta)
                ))
                .append("fechaRespuesta", new java.util.Date());

        collection.insertOne(respuestaDoc);
        System.out.println("Respuesta guardada para usuario: " + usuarioId);
    }
}