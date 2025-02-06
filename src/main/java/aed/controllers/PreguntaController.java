package aed.controllers;

import aed.models.PreguntaPropertyBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class PreguntaController implements Initializable {

    // controllers

    private OpcionController opcionController = new OpcionController();

    // view

    @FXML
    private TableColumn<PreguntaPropertyBean, String> numeroRespuestasColumn;

    @FXML
    private TableColumn<PreguntaPropertyBean, String> preguntaColumn;

    @FXML
    private SplitPane preguntasSplitPane;

    @FXML
    private TableView<PreguntaPropertyBean> preguntasTable;

    @FXML
    private BorderPane root;

    @FXML
    private Label rutaLabel;

    public PreguntaController(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/preguntasView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (Exception e){
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
    void onAnswersAction(ActionEvent event) {

    }

    @FXML
    void onGoBackAction(ActionEvent event) {

    }

    @FXML
    void onModifyAction(ActionEvent event) {

    }

    @FXML
    void onRemoveAction(ActionEvent event) {

    }

    // getters and setters

    public BorderPane getRoot() {
        return root;
    }

    public OpcionController getOpcionController() {
        return opcionController;
    }
}
