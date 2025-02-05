package aed.models;

import org.bson.types.ObjectId;
import java.util.List;

public class Pregunta {
    private ObjectId id;
    private String pregunta;
    private List<ObjectId> opcionesId;

    public Pregunta(ObjectId idEncuesta , String pregunta, List<ObjectId> opciones) {
        this.id = new ObjectId();
        this.pregunta = pregunta;
        this.opcionesId = opciones;
    }

    // Getters y Setters


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public List<ObjectId> getOpcionesId() {
        return opcionesId;
    }

    public void setOpcionesId(List<ObjectId> opcionesId) {
        this.opcionesId = opcionesId;
    }
}
