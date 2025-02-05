package aed.models;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.bson.Document;
import org.bson.types.ObjectId;

public class EncuestaPropertyBean {

    private ObjectProperty<ObjectId> idProperty = new SimpleObjectProperty<>();
    private ObjectProperty<String> tituloProperty = new SimpleObjectProperty<>();
    private ObjectProperty<String> descripcionProperty = new SimpleObjectProperty<>();
    private ListProperty<ObjectId> preguntasIdsProperty = new SimpleListProperty<>(FXCollections.observableArrayList());

    public EncuestaPropertyBean(Document doc) {
        this.idProperty.set(doc.getObjectId("_id"));
        this.tituloProperty.set(doc.getString("titulo"));
        this.descripcionProperty.set(doc.getString("descripcion"));
        this.preguntasIdsProperty.setAll(doc.getList("preguntasIds", ObjectId.class));
    }

    // getters y setters


    public ObjectId getIdProperty() {
        return idProperty.get();
    }

    public ObjectProperty<ObjectId> idPropertyProperty() {
        return idProperty;
    }

    public void setIdProperty(ObjectId idProperty) {
        this.idProperty.set(idProperty);
    }

    public String getTituloProperty() {
        return tituloProperty.get();
    }

    public ObjectProperty<String> tituloPropertyProperty() {
        return tituloProperty;
    }

    public void setTituloProperty(String tituloProperty) {
        this.tituloProperty.set(tituloProperty);
    }

    public String getDescripcionProperty() {
        return descripcionProperty.get();
    }

    public ObjectProperty<String> descripcionPropertyProperty() {
        return descripcionProperty;
    }

    public void setDescripcionProperty(String descripcionProperty) {
        this.descripcionProperty.set(descripcionProperty);
    }

    public ObservableList<ObjectId> getPreguntasIdsProperty() {
        return preguntasIdsProperty.get();
    }

    public ListProperty<ObjectId> preguntasIdsPropertyProperty() {
        return preguntasIdsProperty;
    }

    public void setPreguntasIdsProperty(ObservableList<ObjectId> preguntasIdsProperty) {
        this.preguntasIdsProperty.set(preguntasIdsProperty);
    }
}
