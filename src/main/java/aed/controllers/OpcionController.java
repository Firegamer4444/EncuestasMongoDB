package aed.controllers;

import aed.models.OpcionPropertyBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class OpcionController implements Initializable {

    // view

    @FXML
    private SplitPane opcionesSplitPane;

    @FXML
    private TableView<OpcionPropertyBean> opcionesTable;

    @FXML
    private BorderPane root;

    @FXML
    private Label rutaLabel;

    public OpcionController(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/opcionesView.fxml"));
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
}
