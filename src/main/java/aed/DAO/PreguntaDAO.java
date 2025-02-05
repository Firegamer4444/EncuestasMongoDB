package aed.DAO;

import aed.MongoDBConnection;
import aed.models.OpcionPropertyBean;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.List;

public class PreguntaDAO {
    private final MongoCollection<Document> collection;

    public PreguntaDAO() {
        MongoDatabase database = MongoDBConnection.getDatabase();
        this.collection = database.getCollection("preguntas");
    }

    public ObjectId insertarPregunta(String tituloPregunta) {
        Document pregunta = new Document("tituloPregunta", tituloPregunta);
        collection.insertOne(pregunta);
        return pregunta.getObjectId("_id");
    }

    public void agregarOpcion(String preguntaId, String opcionId) {
        Document pregunta = collection.find(new Document("_id", preguntaId)).first();
        pregunta.append("opcionesIds", opcionId);
        collection.findOneAndReplace(new Document("_id", preguntaId), pregunta);
    }

    public int obtenerNumeroOpciones(String preguntaId) {
        Document pregunta = collection.find(new Document("_id", preguntaId)).first();
        return pregunta.getList("opcionesIds", String.class).size();
    }

    public List<OpcionPropertyBean> obtenerOpciones(ObjectId idPregunta){
        // TODO
    }

}
