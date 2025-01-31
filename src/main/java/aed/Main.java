package aed;

import aed.DAO.AnalisisDAO;
import aed.DAO.EncuestaDAO;
import aed.DAO.RespuestaDAO;

public class Main {
    public static void main(String[] args) {
        EncuestaDAO encuestaDAO = new EncuestaDAO();
        RespuestaDAO respuestaDAO = new RespuestaDAO();
        AnalisisDAO analisisDAO = new AnalisisDAO();

        // Insertar una encuesta
        encuestaDAO.insertarEncuesta("Satisfacción Cliente", "Tu opinión es importante", "p1", "p2");

        // Obtener todas las encuestas
        encuestaDAO.obtenerEncuestas();

        // Guardar respuestas de usuario
        respuestaDAO.insertarRespuesta("65abcd1234ef56789", "user_001", "p1", "Bueno");

        // Analizar respuestas
        analisisDAO.contarRespuestasPorOpcion("p1");

        // Cerrar conexión
        MongoDBConnection.closeConnection();
    }
}