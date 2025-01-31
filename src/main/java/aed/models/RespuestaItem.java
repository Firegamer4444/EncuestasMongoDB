package aed.models;

import org.bson.types.ObjectId;

class RespuestaItem {
    private ObjectId preguntaId;
    private String respuesta;

    public RespuestaItem(ObjectId preguntaId, String respuesta) {
        this.preguntaId = preguntaId;
        this.respuesta = respuesta;
    }

    // Getters y Setters
}
