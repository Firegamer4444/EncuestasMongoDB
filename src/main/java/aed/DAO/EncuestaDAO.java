package aed.DAO;

import aed.MongoDBConnection;
import aed.models.EncuestaPropertyBean;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EncuestaDAO {
    private final MongoCollection<Document> collection;

    public EncuestaDAO() {
        MongoDatabase database = MongoDBConnection.getDatabase();
        this.collection = database.getCollection("encuestas");
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

    public void insertarEncuesta(String titulo, String descripcion, String... preguntasIds) {
        Document encuesta = new Document("titulo", titulo)
                .append("descripcion", descripcion)
                .append("preguntasIds", Arrays.asList(preguntasIds));

        collection.insertOne(encuesta);
    }

}