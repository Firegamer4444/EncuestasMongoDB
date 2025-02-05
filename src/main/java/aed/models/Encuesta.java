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


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<ObjectId> getPreguntasIds() {
        return preguntasIds;
    }

    public void setPreguntasIds(List<ObjectId> preguntasIds) {
        this.preguntasIds = preguntasIds;
    }
}
