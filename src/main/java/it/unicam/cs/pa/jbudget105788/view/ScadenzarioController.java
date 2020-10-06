package it.unicam.cs.pa.jbudget105788.view;

import it.unicam.cs.pa.jbudget105788.application.MainApp;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import it.unicam.cs.pa.jbudget105788.application.*;
import it.unicam.cs.pa.jbudget105788.project.*;

public class ScadenzarioController {
    /**
     * Classe del controller dell omonimo file FXML
     *
     * @author Paolo Campanelli
     *         paolo.campanelli@studenti.unicam.it 105788
     *
     */

	@FXML
    private TableView<Scadenza> scadenze;
    @FXML
    private TableColumn<Scadenza, String> id;
    @FXML
    private TableColumn<Scadenza, String> importoCapitale;
    @FXML
    private TableColumn<Scadenza, String> importoInteressi;
    @FXML
    private TableColumn<Scadenza, String> data;
    @FXML
    private TableColumn<Scadenza, String> tags;
    @FXML
    private TableColumn<Scadenza, String> descrizione;
    @FXML
    private TableColumn<Scadenza, String> pagata;


    private MainApp mainApp;
    private Stage dialogStage;
    private Scadenzario scadenzario;
    /**
     * Inizializza la classe del controllore . uesto metodo è chiamato automaticamente
     * dopo che il file FXML è stato caricato.
     */
@FXML
private void initialize() {
	

	id.setCellValueFactory(
            cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().Id)));
	importoCapitale.setCellValueFactory(
            cellData -> new SimpleStringProperty(cellData.getValue().getImportoCapitale().toString()));
	importoInteressi.setCellValueFactory(
            cellData -> new SimpleStringProperty(cellData.getValue().getImportoInteressi().toString()));
	data.setCellValueFactory(
            cellData -> new SimpleStringProperty(cellData.getValue().getData().toString()));
	tags.setCellValueFactory(
            cellData -> new SimpleStringProperty( cellData.getValue().getTags().toString()));
	descrizione.setCellValueFactory(
            cellData -> new SimpleStringProperty(cellData.getValue().getDescrizione()));
	pagata.setCellValueFactory(
            cellData -> new SimpleStringProperty(cellData.getValue().getIsPagata().toString()));
	
}


    /**
     * Setta lo stage di questa classe.
     *
     * @param dialogStage
     * @param mainApp
     */
public void setDialogStage(MainApp mainApp, Stage dialogStage){
    this.dialogStage = dialogStage;
    this.mainApp = mainApp;

    scadenze.setItems(mainApp.getScadenzarioData()); 

}

    /**
     * Setta lo scadenzario.
     *
     * @param scadenzario
     */
public void setScadenzario(Scadenzario scadenzario) {
    this.scadenzario = scadenzario;
    
}

/**
 * Chiamato quando si preme su aggiungi.
 */
@FXML
private void handleAggiungi() {
	mainApp.showNewScadenza(mainApp.scadenzario);

}

/**
 * Chiamato quando si preme su mostra non pagate.
 */
@FXML
private void handleMostra() {
    ObservableList<Scadenza> filtro = FXCollections.observableArrayList();
    filtro.addAll(mainApp.scadenzario.GetScadenzeNonPagate());
    scadenze.setItems(filtro);

}

/**
 * Chiamato quando si preme indietro.
 */
@FXML
private void handleBack(){
	initialize();
    dialogStage.close();
}
	

}
