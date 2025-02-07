package aed.models;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.bson.Document;
import org.bson.types.ObjectId;

public class PreguntaPropertyBean {

    private ObjectProperty<ObjectId> id = new SimpleObjectProperty<>();
    private StringProperty pregunta = new SimpleStringProperty();
    private ListProperty<ObjectId> opciones = new SimpleListProperty<>(FXCollections.observableArrayList());

    public PreguntaPropertyBean(Document doc) {
        this.id.set(doc.getObjectId("_id"));
        this.pregunta.set(doc.getString("titulo"));
        this.opciones.setAll(doc.getList("opcionesIds", ObjectId.class));
    }

    public PreguntaPropertyBean() {
    }

    // getters and setters


    public ObjectId getId() {
        return id.get();
    }

    public ObjectProperty<ObjectId> idProperty() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id.set(id);
    }

    public String getPregunta() {
        return pregunta.get();
    }

    public StringProperty preguntaProperty() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta.set(pregunta);
    }

    public ObservableList<ObjectId> getOpciones() {
        return opciones.get();
    }

    public ListProperty<ObjectId> opcionesProperty() {
        return opciones;
    }

    public void setOpciones(ObservableList<ObjectId> opciones) {
        this.opciones.set(opciones);
    }
}
