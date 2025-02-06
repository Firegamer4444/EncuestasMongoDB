package aed.controllers;

import aed.models.EncuestaPropertyBean;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static aed.EncuestaApp.encuestaDao;

public class EncuestaController implements Initializable {

    // controllers

    private PreguntaController preguntaController = new PreguntaController();

    // model

    private ListProperty<EncuestaPropertyBean> encuestas = new SimpleListProperty<>(FXCollections.observableArrayList());
    private ObjectProperty<EncuestaPropertyBean> encuestaSeleccionada = new SimpleObjectProperty<>();

    // view

    @FXML
    private TableColumn<EncuestaPropertyBean, String> descriptionColumn;

    @FXML
    private TableView<EncuestaPropertyBean> encuestasTable;

    @FXML
    private TableColumn<EncuestaPropertyBean, String> nameColumn;

    @FXML
    private Button editarButton;

    @FXML
    private Button eliminarButton;

    @FXML
    private Button verPreguntasButton;

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

        // bindings

        encuestasTable.itemsProperty().bind(encuestas);
        encuestaSeleccionada.bind(encuestasTable.getSelectionModel().selectedItemProperty());

        eliminarButton.disableProperty().bind(encuestaSeleccionada.isNull());
        editarButton.disableProperty().bind(encuestaSeleccionada.isNull());
        verPreguntasButton.disableProperty().bind(encuestaSeleccionada.isNull());

        // cell value factories

        nameColumn.setCellValueFactory(cellData -> cellData.getValue().tituloProperty());
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descripcionProperty());


    }

    @FXML
    void onAddAction(ActionEvent event) {
        CrudEncuestaDialog dialog = new CrudEncuestaDialog();
        dialog.showAndWait().ifPresent(encuesta -> {
            encuesta.setId(encuestaDao.insertarEncuesta(encuesta.getTitulo(), encuesta.getDescripcion()));
            encuestas.add(encuesta);
        });
    }

    @FXML
    void onEditAction(ActionEvent event) {
        CrudEncuestaDialog dialog = new CrudEncuestaDialog();
        dialog.getEncuesta().setTitulo(encuestaSeleccionada.get().getTitulo());
        dialog.getEncuesta().setDescripcion(encuestaSeleccionada.get().getDescripcion());
        dialog.showAndWait().ifPresent(encuesta -> {
            encuestaDao.actualizarEncuesta(encuestaSeleccionada.get().getId(), encuesta.getTitulo(), encuesta.getDescripcion());
            encuestas.setAll(encuestaDao.obtenerEncuestas());
        });
    }

    @FXML
    void onEditEncuestaAction(ActionEvent event) {

    }

    @FXML
    void onFindAction(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
    }

    @FXML
    void onRemoveAction(ActionEvent event) {
        encuestaDao.eliminarEncuesta(encuestaSeleccionada.get().getId());
        encuestas.remove(encuestaSeleccionada.get());
    }

    @FXML
    void onSelectAllAction(ActionEvent event) {
        encuestas.setAll(encuestaDao.obtenerEncuestas());
    }

    // getters and setters

    public BorderPane getRoot() {
        return root;
    }

    public PreguntaController getPreguntaController() {
        return preguntaController;
    }
}
