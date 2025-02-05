package aed.models;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import org.bson.Document;
import org.bson.types.ObjectId;

public class PreguntaPropertyBean {

    private ObjectProperty<ObjectId> idProperty = new SimpleObjectProperty<>();
    private StringProperty preguntaProperty;
    private ListProperty<String> opcionesProperty;

    public PreguntaPropertyBean(Document doc) {
        this.idProperty.set(doc.getObjectId("_id"));
        this.preguntaProperty.set(doc.getString("pregunta"));
        this.opcionesProperty.setAll(doc.getList("opciones", String.class));
    }

    public ObjectId getIdProperty() {
        return idProperty.get();
    }

    public ObjectProperty<ObjectId> idPropertyProperty() {
        return idProperty;
    }

    public void setIdProperty(ObjectId idProperty) {
        this.idProperty.set(idProperty);
    }

    public String getPreguntaProperty() {
        return preguntaProperty.get();
    }

    public StringProperty preguntaPropertyProperty() {
        return preguntaProperty;
    }

    public void setPreguntaProperty(String preguntaProperty) {
        this.preguntaProperty.set(preguntaProperty);
    }

    public ListProperty<String> getOpcionesProperty() {
        return opcionesProperty;
    }

    public void setOpcionesProperty(ListProperty<String> opcionesProperty) {
        this.opcionesProperty = opcionesProperty;
    }
}
