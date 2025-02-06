package aed.dao;

import aed.MongoDBConnection;
import aed.models.OpcionPropertyBean;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.List;

public class PreguntaDAO {
    private final MongoCollection<Document> collection;
    private final MongoCollection<Document> opcionesCollection;
    private final MongoCollection<Document> respuestasCollection;

    public PreguntaDAO() {
        MongoDatabase database = MongoDBConnection.getDatabase();
        this.collection = database.getCollection("preguntas");
        this.opcionesCollection = database.getCollection("opciones");
        this.respuestasCollection = database.getCollection("respuestas");
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

    public int obtenerNumeroRespuestasOpcion(ObjectId opcionId){
        return (int) respuestasCollection.countDocuments(new Document("opcionId", opcionId));
    }

    public List<OpcionPropertyBean> obtenerOpciones(ObjectId idPregunta){
        List<OpcionPropertyBean> opciones = null;

        Document pregunta = collection.find(new Document("_id", idPregunta)).first();
        List<ObjectId> opcionesIds = pregunta.getList("opcionesIds", ObjectId.class);
        for (ObjectId opcionId : opcionesIds) {
            Document opcion = opcionesCollection.find(new Document("_id", opcionId)).first();
            OpcionPropertyBean opcionBean = new OpcionPropertyBean(opcion.getString("tituloOpcion"), obtenerNumeroRespuestasOpcion(opcionId));
            opciones.add(opcionBean);
        }

        return opciones;
    }

}
