package aed;

import aed.controllers.EncuestaController;
import aed.controllers.OpcionController;
import aed.controllers.PreguntaController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EncuestaApp extends Application {

    // controllers

    private static EncuestaController encuestaController = new EncuestaController();
    private static PreguntaController preguntaController = encuestaController.getPreguntaController();
    private static OpcionController opcionController = preguntaController.getOpcionController();

    // stages

    private static Stage encuestaStage;
    private static Stage preguntaStage = new Stage();
    private static Stage opcionStage = new Stage();

    @Override
    public void start(Stage stage) throws Exception {

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
