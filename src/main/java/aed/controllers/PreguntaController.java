package aed.controllers;

import aed.EncuestaApp;
import aed.models.PreguntaPropertyBean;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import org.bson.types.ObjectId;

import java.net.URL;
import java.util.ResourceBundle;

import static aed.EncuestaApp.encuestaDao;
import static aed.EncuestaApp.preguntaDao;

public class PreguntaController implements Initializable {

    // controllers

    private OpcionController opcionController = new OpcionController();

    // model

    private StringProperty ruta = new SimpleStringProperty();
    private ObjectProperty<ObjectId> idEncuesta = new SimpleObjectProperty<>();
    private ListProperty<PreguntaPropertyBean> preguntas = new SimpleListProperty<>(FXCollections.observableArrayList());
    private ObjectProperty<PreguntaPropertyBean> preguntaSeleccionada = new SimpleObjectProperty<>();

    // view

    @FXML
    private TableColumn<PreguntaPropertyBean, Number> numeroPreguntasColumn;

    @FXML
    private TableColumn<PreguntaPropertyBean, String> preguntaColumn;

    @FXML
    private TableView<PreguntaPropertyBean> preguntasTable;

    @FXML
    private BorderPane root;

    @FXML
    private Label rutaLabel;

    @FXML
    private Button editarButton;

    @FXML
    private Button verRespuestasButton;

    @FXML
    private Button eliminarButton;



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
        // bindings

        rutaLabel.textProperty().bind(ruta);
        preguntasTable.itemsProperty().bind(preguntas);
        preguntaSeleccionada.bind(preguntasTable.getSelectionModel().selectedItemProperty());

        eliminarButton.disableProperty().bind(preguntaSeleccionada.isNull());
        editarButton.disableProperty().bind(preguntaSeleccionada.isNull());
        verRespuestasButton.disableProperty().bind(preguntaSeleccionada.isNull());

        opcionController.opcionesProperty().addListener((o, ov, nv) -> {
            preguntas.setAll(encuestaDao.obtenerPreguntas(idEncuesta.get()));
        });

        // cell value factories

        preguntaColumn.setCellValueFactory(cellData -> cellData.getValue().preguntaProperty());
        numeroPreguntasColumn.setCellValueFactory(cellData -> cellData.getValue().opcionesProperty().sizeProperty());

    }

    @FXML
    void onAddAction(ActionEvent event) {
        CrudTituloDialog dialog = new CrudTituloDialog();
        dialog.showAndWait().ifPresent(titulo -> {
            PreguntaPropertyBean pregunta = new PreguntaPropertyBean();
            encuestaDao.agregarPregunta(idEncuesta.get(), titulo);
            pregunta.setPregunta(titulo);
            preguntas.add(pregunta);
        });
    }

    @FXML
    void onAnswersAction(ActionEvent event) {
        opcionController.setRuta(ruta.get() + " > " + preguntaSeleccionada.get().getPregunta());
        opcionController.setIdPregunta(preguntaSeleccionada.get().getId());
        opcionController.getOpciones().setAll(preguntaDao.obtenerOpciones(preguntaSeleccionada.get().getId()));
        EncuestaApp.mostrarOpcion();
    }

    @FXML
    void onGoBackAction(ActionEvent event) {
        EncuestaApp.mostrarEncuesta();
    }

    @FXML
    void onModifyAction(ActionEvent event) {
        CrudTituloDialog dialog = new CrudTituloDialog();;
        dialog.setTitulo(preguntaSeleccionada.get().getPregunta());
        dialog.showAndWait().ifPresent(titulo -> {
            preguntaDao.modificarPregunta(preguntaSeleccionada.get().getId(), titulo);
            preguntaSeleccionada.get().setPregunta(titulo);
        });
    }

    @FXML
    void onRemoveAction(ActionEvent event) {
        encuestaDao.eliminarPregunta(idEncuesta.get() , preguntaSeleccionada.get().getId());
        preguntas.remove(preguntaSeleccionada.get());
    }

    @FXML
    void onSelectAllAction(ActionEvent event) {
        preguntas.setAll(encuestaDao.obtenerPreguntas(idEncuesta.get()));
    }

    // getters and setters

    public BorderPane getRoot() {
        return root;
    }

    public OpcionController getOpcionController() {
        return opcionController;
    }

    public ObservableList<PreguntaPropertyBean> getPreguntas() {
        return preguntas.get();
    }

    public ListProperty<PreguntaPropertyBean> preguntasProperty() {
        return preguntas;
    }

    public void setPreguntas(ObservableList<PreguntaPropertyBean> preguntas) {
        this.preguntas.set(preguntas);
    }

    public String getRuta() {
        return ruta.get();
    }

    public StringProperty rutaProperty() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta.set(ruta);
    }

    public ObjectId getIdEncuesta() {
        return idEncuesta.get();
    }

    public ObjectProperty<ObjectId> idEncuestaProperty() {
        return idEncuesta;
    }

    public void setIdEncuesta(ObjectId idEncuesta) {
        this.idEncuesta.set(idEncuesta);
    }
}
