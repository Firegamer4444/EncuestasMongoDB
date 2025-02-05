package aed.models;

import org.bson.types.ObjectId;

public class Opcion {

    private ObjectId id;
    private String tittuloOpcion;

    public Opcion(String tittuloOpcion) {
        this.id = new ObjectId();
        this.tittuloOpcion = tittuloOpcion;
    }

    // getters and setters

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTittuloOpcion() {
        return tittuloOpcion;
    }

    public void setTittuloOpcion(String tittuloOpcion) {
        this.tittuloOpcion = tittuloOpcion;
    }
}
