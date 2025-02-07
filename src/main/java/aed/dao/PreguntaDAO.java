package aed.dao;

import aed.MongoDBConnection;
import aed.models.OpcionPropertyBean;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
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

    public void modificarPregunta(ObjectId idPregunta, String pregunta) {
        Document preguntaDoc = collection.find(new Document("_id", idPregunta)).first();
        preguntaDoc.append("pregunta", pregunta);
        collection.findOneAndReplace(new Document("_id", idPregunta), preguntaDoc);
    }

    public ObjectId agregarOpcion(ObjectId preguntaId, String titulo) {
        Document opcion = new Document("tituloOpcion", titulo);
        opcionesCollection.insertOne(opcion);

        Document pregunta = collection.find(new Document("_id", preguntaId)).first();
        List<ObjectId> opcionesIds = pregunta.getList("opcionesIds", ObjectId.class);
        if (opcionesIds == null) {
            opcionesIds = new ArrayList<>();
        }
        opcionesIds.add(opcion.getObjectId("_id"));
        pregunta.append("opcionesIds", opcionesIds);
        collection.findOneAndReplace(new Document("_id", preguntaId), pregunta);

        return opcion.getObjectId("_id");
    }

    public int obtenerNumeroOpciones(ObjectId preguntaId) {
        Document pregunta = collection.find(new Document("_id", preguntaId)).first();
        List<ObjectId> opcionesIds = pregunta.getList("opcionesIds", ObjectId.class);
        if (opcionesIds == null) {
            return 0;
        }
        return opcionesIds.size();
    }

    public int obtenerNumeroRespuestasOpcion(ObjectId opcionId){
        int contador = 0;
        FindIterable<Document> respuestas = respuestasCollection.find(Filters.eq("opcionId", opcionId.toString()));
        for (Document doc : respuestas) {
            contador++;
        }
        return contador;
    }

    public List<OpcionPropertyBean> obtenerOpciones(ObjectId idPregunta) {
        List<OpcionPropertyBean> opciones = new ArrayList<>();
        Document pregunta = collection.find(new Document("_id", idPregunta)).first();
        List<ObjectId> opcionesIds = pregunta.getList("opcionesIds", ObjectId.class);
        if (opcionesIds == null) {
            return opciones;
        }

        for (ObjectId opcionId : opcionesIds) {
            Document opcion = opcionesCollection.find(new Document("_id", opcionId)).first();
            OpcionPropertyBean opcionBean = new OpcionPropertyBean(opcion);
            opciones.add(opcionBean);
        }

        return opciones;
    }

    public void modificarOpcion( ObjectId idPregunta, ObjectId idOpcion, String tituloOpcion) {
        Document pregunta = collection.find(new Document("_id", idPregunta)).first();
        List<ObjectId> opcionesIds = pregunta.getList("opcionesIds", ObjectId.class);
        for (ObjectId opcionId : opcionesIds) {
            if (opcionId.equals(idOpcion)) {
                Document opcion = opcionesCollection.find(new Document("_id", idOpcion)).first();
                opcion.append("tituloOpcion", tituloOpcion);
                opcionesCollection.findOneAndReplace(new Document("_id", idOpcion), opcion);
            }
        }

    }

    public void eliminarOpcion(ObjectId idPregunta, ObjectId idOpcion) {
        Document pregunta = collection.find(new Document("_id", idPregunta)).first();
        List<ObjectId> opcionesIds = pregunta.getList("opcionesIds", ObjectId.class);
        opcionesIds.remove(idOpcion);
        pregunta.append("opcionesIds", opcionesIds);
        collection.findOneAndReplace(new Document("_id", idPregunta), pregunta);
        // Eliminar respuestas
        respuestasCollection.deleteMany(Filters.eq("opcionId", idOpcion.toString()));

        Document opcion = opcionesCollection.find(new Document("_id", idOpcion)).first();
        opcionesCollection.deleteOne(opcion);
    }

}
