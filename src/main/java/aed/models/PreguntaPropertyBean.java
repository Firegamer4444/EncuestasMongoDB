package aed.models;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import org.bson.Document;
import org.bson.types.ObjectId;

public class PreguntaPropertyBean {

    private ObjectProperty<ObjectId> id = new SimpleObjectProperty<>();
    private StringProperty pregunta;
    private ListProperty<String> opciones;

    public PreguntaPropertyBean(Document doc) {
        this.id.set(doc.getObjectId("_id"));
        this.pregunta.set(doc.getString("pregunta"));
        this.opciones.setAll(doc.getList("opciones", String.class));
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

    public ObservableList<String> getOpciones() {
        return opciones.get();
    }

    public ListProperty<String> opcionesProperty() {
        return opciones;
    }

    public void setOpciones(ObservableList<String> opciones) {
        this.opciones.set(opciones);
    }
}
