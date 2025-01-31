package aed.models;

import org.bson.types.ObjectId;

import java.util.List;

public class Encuesta {
    private ObjectId id;
    private String titulo;
    private String descripcion;
    private List<ObjectId> preguntasIds;

    public Encuesta(String titulo, String descripcion, List<ObjectId> preguntasIds) {
        this.id = new ObjectId();
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.preguntasIds = preguntasIds;
    }

    // Getters y Setters
}
