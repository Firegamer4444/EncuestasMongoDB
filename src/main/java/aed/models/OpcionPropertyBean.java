package aed.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class OpcionPropertyBean {

    private StringProperty tituloRespuesta = new SimpleStringProperty();
    private StringProperty porcentaje = new SimpleStringProperty();
    private IntegerProperty respuestasTotales = new SimpleIntegerProperty();
    private IntegerProperty respuestasOpcion = new SimpleIntegerProperty();

    public OpcionPropertyBean(String tituloRespuesta, int respuestasOpcion) {
        this.tituloRespuesta.set(tituloRespuesta);
        this.respuestasOpcion.set(respuestasOpcion);
    }

}
