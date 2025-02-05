package aed.models;


import org.bson.types.ObjectId;
import java.util.Date;
import java.util.List;

public class Respuesta {
    private ObjectId id;
    private ObjectId preguntaId;
    private String usuarioId;
    private ObjectId opcionId;

    public Respuesta(ObjectId preguntaId, String usuarioId, ObjectId opcionId) {
        this.id = new ObjectId();
        this.preguntaId = preguntaId;
        this.usuarioId = usuarioId;
        this.opcionId = opcionId;
    }

    // Getters y Setters


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getPreguntaId() {
        return preguntaId;
    }

    public void setPreguntaId(ObjectId preguntaId) {
        this.preguntaId = preguntaId;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public ObjectId getOpcionId() {
        return opcionId;
    }

    public void setOpcionId(ObjectId opcionId) {
        this.opcionId = opcionId;
    }
}
