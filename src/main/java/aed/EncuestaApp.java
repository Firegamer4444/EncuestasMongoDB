package aed;

import aed.controllers.EncuestaController;
import aed.controllers.OpcionController;
import aed.controllers.PreguntaController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import aed.dao.*;
import org.bson.types.ObjectId;

public class EncuestaApp extends Application {

    // controllers

    private static EncuestaController encuestaController = new EncuestaController();
    private static PreguntaController preguntaController = encuestaController.getPreguntaController();
    private static OpcionController opcionController = preguntaController.getOpcionController();

    // stages

    private static Stage encuestaStage;
    private static Stage preguntaStage = new Stage();
    private static Stage opcionStage = new Stage();

    // Daos

    public static EncuestaDAO encuestaDao = new EncuestaDAO();
    public static PreguntaDAO preguntaDao = new PreguntaDAO();
    public static RespuestaDAO respuestaDao = new RespuestaDAO();

    @Override
    public void start(Stage stage) throws Exception {
        // insertar datos de prueba
        /*
        ObjectId encuesta1Id = encuestaDao.insertarEncuesta("Encuesta 1", "Descripción de la Encuesta 1");
        ObjectId encuesta2Id = encuestaDao.insertarEncuesta("Encuesta 2", "Descripción de la Encuesta 2");
        ObjectId encuesta3Id = encuestaDao.insertarEncuesta("Encuesta 3", "Descripción de la Encuesta 3");

        ObjectId pregunta1Id = encuestaDao.agregarPregunta(encuesta1Id, "Pregunta 1 de Encuesta 1");
        ObjectId pregunta2Id = encuestaDao.agregarPregunta(encuesta1Id, "Pregunta 2 de Encuesta 1");
        ObjectId pregunta3Id = encuestaDao.agregarPregunta(encuesta2Id, "Pregunta 1 de Encuesta 2");

        ObjectId opcion1Id = preguntaDao.agregarOpcion(pregunta1Id, "Opción 1 de Pregunta 1");
        ObjectId opcion2Id = preguntaDao.agregarOpcion(pregunta1Id, "Opción 2 de Pregunta 1");
        ObjectId opcion3Id = preguntaDao.agregarOpcion(pregunta2Id, "Opción 1 de Pregunta 2");

        respuestaDao.insertarRespuesta(pregunta1Id.toString(), "Usuario1", opcion1Id.toString());
        respuestaDao.insertarRespuesta(pregunta1Id.toString(), "Usuario2", opcion2Id.toString());
        respuestaDao.insertarRespuesta(pregunta2Id.toString(), "Usuario1", opcion3Id.toString());
        */

        encuestaStage = stage;

        Scene scene = new Scene(encuestaController.getRoot());

        stage.setScene(scene);
        preguntaStage.setScene(new Scene(preguntaController.getRoot()));
        opcionStage.setScene(new Scene(opcionController.getRoot()));

        stage.setTitle("Encuestas");
        preguntaStage.setTitle("Preguntas");
        opcionStage.setTitle("Opciones");

        stage.show();
    }

    public static void mostrarPregunta(){
        encuestaStage.hide();
        opcionStage.hide();
        preguntaStage.show();
    }

    public static void mostrarOpcion(){
        preguntaStage.hide();
        opcionStage.show();
    }

    public static void mostrarEncuesta(){
        preguntaStage.hide();
        encuestaStage.show();
    }

}
