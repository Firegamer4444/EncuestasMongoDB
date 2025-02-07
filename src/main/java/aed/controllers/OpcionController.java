package aed.controllers;

import aed.EncuestaApp;
import aed.models.OpcionPropertyBean;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import org.bson.types.ObjectId;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static aed.EncuestaApp.preguntaDao;

public class OpcionController implements Initializable {


    // model

    private ObjectProperty<ObjectId> idPregunta = new SimpleObjectProperty<>();
    private StringProperty ruta = new SimpleStringProperty();
    private ListProperty<OpcionPropertyBean> opciones = new SimpleListProperty<>(FXCollections.observableArrayList());
    private ObjectProperty<OpcionPropertyBean> opcionSeleccionada = new SimpleObjectProperty<>();

    // view

    @FXML
    private TableColumn<OpcionPropertyBean, String> opcionColumn;

    @FXML
    private TableView<OpcionPropertyBean> opcionesTable;

    @FXML
    private TableColumn<OpcionPropertyBean, String> porrcentajeColumn;

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

        // bindings

        rutaLabel.textProperty().bind(ruta);
        opcionesTable.itemsProperty().bind(opciones);
        opcionSeleccionada.bind(opcionesTable.getSelectionModel().selectedItemProperty());

        opciones.addListener((o, ov, nv) -> bindearOpciones());

        // cell values factory

        opcionColumn.setCellValueFactory(cellData -> cellData.getValue().tituloRespuestaProperty());
        porrcentajeColumn.setCellValueFactory(cellData -> cellData.getValue().porcentajeProperty());


    }

    @FXML
    void onAddAction(ActionEvent event) {
        CrudTituloDialog dialog = new CrudTituloDialog();
        dialog.showAndWait().ifPresent(titulo -> {
            ObjectId idOpcion = preguntaDao.agregarOpcion(getIdPregunta(), titulo);
            OpcionPropertyBean opcion = new OpcionPropertyBean();
            opcion.setId(idOpcion);
            opcion.setTituloRespuesta(titulo);
            getOpciones().add(opcion);
        });
    }

    @FXML
    void onGoBackAction(ActionEvent event) {
        EncuestaApp.mostrarPregunta();
    }

    @FXML
    void onModifyAction(ActionEvent event) {
        CrudTituloDialog dialog = new CrudTituloDialog();
        dialog.showAndWait().ifPresent(titulo -> {
            preguntaDao.modificarOpcion(getIdPregunta(), opcionSeleccionada.get().getId(), titulo);
            opcionSeleccionada.get().setTituloRespuesta(titulo);
        });
    }

    @FXML
    void onRemoveAction(ActionEvent event) {
        preguntaDao.eliminarOpcion(getIdPregunta(), opcionSeleccionada.get().getId());
        getOpciones().remove(opcionSeleccionada.get());
    }

    @FXML
    void onSelectAllAction(ActionEvent event) {
        opciones.setAll(preguntaDao.obtenerOpciones(getIdPregunta()));
    }

    public void bindearOpciones(){
        for (OpcionPropertyBean opcion : getOpciones()) {
            opcion.porcentajeProperty().bind(Bindings.createStringBinding(() -> {
                int total = preguntaDao.obtenerNumeroRespuestasOpcion(opcion.getId());
                int totalPreguntas = preguntaDao.obtenerNumeroOpciones(getIdPregunta());
                return total == 0 ? "0%" : String.format("%.2f%%", (total * 100.0) / totalPreguntas);
            }, getOpciones()));
        }
    }

    // getters and setters

    public BorderPane getRoot() {
        return root;
    }

    public ObjectId getIdPregunta() {
        return idPregunta.get();
    }

    public ObjectProperty<ObjectId> idPreguntaProperty() {
        return idPregunta;
    }

    public void setIdPregunta(ObjectId idPregunta) {
        this.idPregunta.set(idPregunta);
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

    public ObservableList<OpcionPropertyBean> getOpciones() {
        return opciones.get();
    }

    public ListProperty<OpcionPropertyBean> opcionesProperty() {
        return opciones;
    }

    public void setOpciones(ObservableList<OpcionPropertyBean> opciones) {
        this.opciones.set(opciones);
    }
}
