package aed.dao;

import aed.MongoDBConnection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class RespuestaDAO {
    private final MongoCollection<Document> collection;

    public RespuestaDAO() {
        MongoDatabase database = MongoDBConnection.getDatabase();
        this.collection = database.getCollection("respuestas");
    }

    public void insertarRespuesta(String preguntaId, String usuarioId, String opcionId) {
        Document respuesta = new Document("preguntaId", preguntaId)
                .append("usuarioId", usuarioId)
                .append("opcionId", opcionId);

        collection.insertOne(respuesta);
    }

    public int obtenerNumeroRespuestas(String preguntaId) {
        return (int) collection.countDocuments(new Document("preguntaId", preguntaId));
    }

    public int obtenerNumeroRespuestasPorOpcion(String preguntaId, String opcionId) {
        return (int) collection.countDocuments(new Document("preguntaId", preguntaId).append("opcionId", opcionId));
    }

}