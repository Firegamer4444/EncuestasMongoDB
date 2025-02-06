package aed.controllers;

import aed.models.EncuestaPropertyBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EncuestaController implements Initializable {

    // controllers

    private PreguntaController preguntaController = new PreguntaController();

    // view

    @FXML
    private ListView<EncuestaPropertyBean> encuestasList;

    @FXML
    private SplitPane encuestasSplitPane;

    @FXML
    private BorderPane root;

    public EncuestaController(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/encuestasView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e){
            throw  new RuntimeException();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void onAddAction(ActionEvent event) {

    }

    @FXML
    void onEditAction(ActionEvent event) {

    }

    @FXML
    void onEditEncuestaAction(ActionEvent event) {

    }

    @FXML
    void onRemoveAction(ActionEvent event) {

    }

    @FXML
    void onStadisticsAction(ActionEvent event) {

    }

    // getters and setters

    public BorderPane getRoot() {
        return root;
    }

    public PreguntaController getPreguntaController() {
        return preguntaController;
    }
}
