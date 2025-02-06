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
        List<String> preguntasIds = encuesta.getList("preguntasIds", String.class);
        for (String preguntaId : preguntasIds) {
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

}