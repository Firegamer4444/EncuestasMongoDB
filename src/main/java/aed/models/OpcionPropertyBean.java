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

    public int getRespuestasTotales() {
        return respuestasTotales.get();
    }

    public IntegerProperty respuestasTotalesProperty() {
        return respuestasTotales;
    }

    public void setRespuestasTotales(int respuestasTotales) {
        this.respuestasTotales.set(respuestasTotales);
    }

    public int getRespuestasOpcion() {
        return respuestasOpcion.get();
    }

    public IntegerProperty respuestasOpcionProperty() {
        return respuestasOpcion;
    }

    public void setRespuestasOpcion(int respuestasOpcion) {
        this.respuestasOpcion.set(respuestasOpcion);
    }
}
