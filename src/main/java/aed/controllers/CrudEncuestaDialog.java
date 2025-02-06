package aed.controllers;

import aed.models.EncuestaPropertyBean;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CrudEncuestaDialog extends Dialog<EncuestaPropertyBean> implements Initializable {


    // model

    private EncuestaPropertyBean encuesta = new EncuestaPropertyBean();

    // view

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private TextField nameField;

    @FXML
    private GridPane root;

    public CrudEncuestaDialog(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/crudEncuestaView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // set dialog

        setTitle("Encuesta");
        getDialogPane().setContent(root);
        getDialogPane().getButtonTypes().setAll(new ButtonType("Confirmar" , ButtonBar.ButtonData.OK_DONE) , ButtonType.CANCEL);
        setResultConverter(this::onResult);

        // bindings

        nameField.textProperty().bindBidirectional(encuesta.tituloProperty());
        descriptionTextArea.textProperty().bindBidirectional(encuesta.descripcionProperty());

    }

    private EncuestaPropertyBean onResult(ButtonType buttonType) {
        if(buttonType.getButtonData() == ButtonBar.ButtonData.OK_DONE){
            return encuesta;
        }
        return null;
    }

    // getters and setters

    public EncuestaPropertyBean getEncuesta() {
        return encuesta;
    }

}
