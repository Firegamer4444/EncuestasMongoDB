package aed.models;

import javafx.beans.property.*;
import org.bson.Document;
import org.bson.types.ObjectId;

public class OpcionPropertyBean {

    private ObjectProperty<ObjectId> id = new SimpleObjectProperty<>();
    private StringProperty tituloRespuesta = new SimpleStringProperty();
    private StringProperty porcentaje = new SimpleStringProperty();

    public OpcionPropertyBean (Document document){
        this.id.set(document.getObjectId("_id"));
        this.tituloRespuesta.set(document.getString("tituloOpcion"));
    }

    public OpcionPropertyBean() {
    }


    // getters and setters


    public String getTituloRespuesta() {
        return tituloRespuesta.get();
    }

    public StringProperty tituloRespuestaProperty() {
        return tituloRespuesta;
    }

    public void setTituloRespuesta(String tituloRespuesta) {
        this.tituloRespuesta.set(tituloRespuesta);
    }

    public String getPorcentaje() {
        return porcentaje.get();
    }

    public StringProperty porcentajeProperty() {
        return porcentaje;
    }

    public void setPorcentaje(String porcentaje) {
        this.porcentaje.set(porcentaje);
    }

    public ObjectId getId() {
        return id.get();
    }

    public ObjectProperty<ObjectId> idProperty() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id.set(id);
    }
}
