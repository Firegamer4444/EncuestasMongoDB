package aed.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class CrudTituloDialog extends Dialog<String> implements Initializable {

    // model

    private StringProperty titulo = new SimpleStringProperty();

    // view

    @FXML
    private GridPane root;

    @FXML
    private TextField tituloTextField;

    public CrudTituloDialog(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/tituloView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (Exception e){
            throw new RuntimeException();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // set dialog

        setTitle("Encuesta");
        getDialogPane().setContent(root);
        getDialogPane().getButtonTypes().setAll(new ButtonType("Confirmar" , ButtonBar.ButtonData.OK_DONE) , ButtonType.CANCEL);
        setResultConverter(this::onResult);

        // biundings

        tituloTextField.textProperty().bindBidirectional(titulo);

    }

    private String onResult(ButtonType buttonType) {
        if(buttonType.getButtonData() == ButtonBar.ButtonData.OK_DONE){
            return titulo.get();
        }
        return null;
    }

    // getters and setters


    public String getTitulo() {
        return titulo.get();
    }

    public StringProperty tituloProperty() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo.set(titulo);
    }
}
