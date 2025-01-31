package aed.models;

import org.bson.types.ObjectId;
import java.util.List;

public class Pregunta {
    private ObjectId id;
    private String pregunta;
    private String tipo; // "opcion_multiple" o "texto"
    private List<String> opciones;

    public Pregunta(String pregunta, String tipo, List<String> opciones) {
        this.id = new ObjectId();
        this.pregunta = pregunta;
        this.tipo = tipo;
        this.opciones = opciones;
    }

    // Getters y Setters
}
