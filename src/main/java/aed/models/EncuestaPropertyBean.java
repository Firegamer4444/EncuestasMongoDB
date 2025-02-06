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

    private ObjectProperty<ObjectId> id = new SimpleObjectProperty<>();
    private ObjectProperty<String> titulo = new SimpleObjectProperty<>();
    private ObjectProperty<String> descripcion = new SimpleObjectProperty<>();
    private ListProperty<ObjectId> preguntasIds = new SimpleListProperty<>(FXCollections.observableArrayList());

    public EncuestaPropertyBean(Document doc) {
        this.id.set(doc.getObjectId("_id"));
        this.titulo.set(doc.getString("titulo"));
        this.descripcion.set(doc.getString("descripcion"));
        this.preguntasIds.setAll(doc.getList("preguntasIds", ObjectId.class));
    }

    public EncuestaPropertyBean() {
    }

    // getters y setters


    public ObjectId getId() {
        return id.get();
    }

    public ObjectProperty<ObjectId> idProperty() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id.set(id);
    }

    public String getTitulo() {
        return titulo.get();
    }

    public ObjectProperty<String> tituloProperty() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo.set(titulo);
    }

    public String getDescripcion() {
        return descripcion.get();
    }

    public ObjectProperty<String> descripcionProperty() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }

    public ObservableList<ObjectId> getPreguntasIds() {
        return preguntasIds.get();
    }

    public ListProperty<ObjectId> preguntasIdsProperty() {
        return preguntasIds;
    }

    public void setPreguntasIds(ObservableList<ObjectId> preguntasIds) {
        this.preguntasIds.set(preguntasIds);
    }
}
