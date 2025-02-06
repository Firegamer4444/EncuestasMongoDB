package aed.dao;

import aed.MongoDBConnection;
import aed.models.EncuestaPropertyBean;
import aed.models.PreguntaPropertyBean;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EncuestaDAO {
    private final MongoCollection<Document> collection;
    private final MongoCollection<Document> preguntasCollection;

    public EncuestaDAO() {
        MongoDatabase database = MongoDBConnection.getDatabase();
        this.collection = database.getCollection("encuestas");
        this.preguntasCollection = database.getCollection("preguntas");
    }

    public List<EncuestaPropertyBean> obtenerEncuestas() {
        List<EncuestaPropertyBean> encuestas = new ArrayList<>();
        FindIterable<Document> encuestasDocs = collection.find();

        for (Document doc : encuestasDocs) {
            EncuestaPropertyBean encuesta = new EncuestaPropertyBean(doc);
            encuestas.add(encuesta);
        }

        return encuestas;
    }

    public List<PreguntaPropertyBean> obtenerPreguntas(ObjectId encuestaId){
        List<PreguntaPropertyBean> preguntas = new ArrayList<>();
        Document encuesta = collection.find(new Document("_id", encuestaId)).first();
        List<ObjectId> preguntasIds = encuesta.getList("preguntasIds", ObjectId.class);

        for (ObjectId preguntaId : preguntasIds) {
            Document pregunta = preguntasCollection.find(new Document("_id", preguntaId)).first();
            PreguntaPropertyBean preguntaBean = new PreguntaPropertyBean(pregunta);
            preguntas.add(preguntaBean);
        }

        return preguntas;
    }

    public ObjectId insertarEncuesta(String titulo, String descripcion) {
        Document encuesta = new Document("titulo", titulo)
                .append("descripcion", descripcion)
                .append("preguntasIds", Collections.emptyList());

        collection.insertOne(encuesta);
        return encuesta.getObjectId("_id");
    }

    public void actualizarEncuesta(ObjectId encuestaId, String titulo, String descripcion) {
        Document encuesta = collection.find(new Document("_id", encuestaId)).first();
        encuesta.append("titulo", titulo).append("descripcion", descripcion);
        collection.findOneAndReplace(new Document("_id", encuestaId), encuesta);
    }

    public void eliminarEncuesta(ObjectId encuestaId) {
        Document encuesta = collection.find(new Document("_id", encuestaId)).first();
        collection.deleteOne(encuesta);
    }

    public ObjectId agregarPregunta(ObjectId encuestaId, String titulo) {
        Document pregunta = new Document("titulo", titulo)
                .append("opciones", Collections.emptyList());

        preguntasCollection.insertOne(pregunta);
        ObjectId preguntaId = pregunta.getObjectId("_id");

        Document encuesta = collection.find(new Document("_id", encuestaId)).first();
        List<ObjectId> preguntasIds = encuesta.getList("preguntasIds", ObjectId.class);
        preguntasIds.add(preguntaId);
        encuesta.append("preguntasIds", preguntasIds);
        collection.findOneAndReplace(new Document("_id", encuestaId), encuesta);

        return preguntaId;
    }

    public void eliminarPregunta(ObjectId encuestaId, ObjectId preguntaId) {
        Document encuesta = collection.find(new Document("_id", encuestaId)).first();
        Document pregunta = preguntasCollection.find(new Document("_id", preguntaId)).first();
        preguntasCollection.deleteOne(pregunta);
        encuesta.getList("preguntasIds", ObjectId.class).remove(preguntaId);
        collection.findOneAndReplace(new Document("_id", encuestaId), encuesta);
    }

    public List<EncuestaPropertyBean> buscarEncuesta(String titulo){
        List<EncuestaPropertyBean> encuestas = new ArrayList<>();
        FindIterable<Document> encuestasDocs = collection.find(new Document("titulo", new Document("$regex", titulo).append("$options", "i")));;

        for (Document doc : encuestasDocs) {
            EncuestaPropertyBean encuesta = new EncuestaPropertyBean(doc);
            encuestas.add(encuesta);
        }

        return encuestas;
    }

}