package aed.models;

import aed.RespuestaItem;
import org.bson.types.ObjectId;
import java.util.Date;
import java.util.List;

public class Respuesta {
    private ObjectId id;
    private ObjectId encuestaId;
    private String usuarioId;
    private List<RespuestaItem> respuestas;
    private Date fechaRespuesta;

    public Respuesta(ObjectId encuestaId, String usuarioId, List<RespuestaItem> respuestas) {
        this.id = new ObjectId();
        this.encuestaId = encuestaId;
        this.usuarioId = usuarioId;
        this.respuestas = respuestas;
        this.fechaRespuesta = new Date();
    }

    // Getters y Setters
}
