package it.unicam.cs.pa.jbudget105788.view;

import it.unicam.cs.pa.jbudget105788.application.MainApp;
import it.unicam.cs.pa.jbudget105788.project.ContoCorrente;
import it.unicam.cs.pa.jbudget105788.project.Struttura;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import it.unicam.cs.pa.jbudget105788.project.*;

public class RicercheCCController {
    /**
     * Classe del controller dell omonimo file FXML
     *
     * @author Paolo Campanelli
     *         paolo.campanelli@studenti.unicam.it 105788
     *
     */

	@FXML
    private TableView<MovContoCorrente> conto;
    @FXML
    private TableColumn<MovContoCorrente, String> id;
    @FXML
    private TableColumn<MovContoCorrente, String> importo;
    @FXML
    private TableColumn<MovContoCorrente, String> data;
    @FXML
    private TableColumn<MovContoCorrente, String> causale;
    @FXML
    private TableColumn<MovContoCorrente, String> tags;
    @FXML
    private TableColumn<MovContoCorrente, String> categoria;
    
    @FXML
    private DatePicker dataInizioField;
    @FXML
    private DatePicker dataFineField;
    @FXML
    private TextField tagField;
    @FXML
    private TextField categoriaField;

    private MainApp mainApp;
    private Stage dialogStage;
    private Struttura struttura;
    private ContoCorrente contoCorrente;
    /**
     * Inizializza la classe del controllore . questo metodo è chiamato automaticamente
     * dopo che il file FXML è stato caricato.
     */
@FXML
private void initialize() {
	

	id.setCellValueFactory(
            cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().Id)));
	importo.setCellValueFactory(
            cellData -> new SimpleStringProperty(cellData.getValue().getImporto().toString()));
	data.setCellValueFactory(
            cellData -> new SimpleStringProperty(cellData.getValue().getData().toString()));
	causale.setCellValueFactory(
            cellData -> new SimpleStringProperty(cellData.getValue().getCausale()));
	categoria.setCellValueFactory(
            cellData -> new SimpleStringProperty(cellData.getValue().getCategoria().Codice));
	tags.setCellValueFactory(
            cellData -> new SimpleStringProperty( cellData.getValue().getTags().toString()));
	dataInizioField.setValue(LocalDate.now());
	dataFineField.setValue(LocalDate.now());
	
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
    
}

/**
 * Setta la struttura.
 *
 * @param struttura
 */
public void setCategoria(Struttura struttura) {
    this.struttura = struttura;
}

    /**
     * Setta il conto corrente.
     *
     * @param contoCorrente
     */
public void setContoCorrente(ContoCorrente contoCorrente) {
    this.contoCorrente = contoCorrente;
}

    /**
     * Chiamato quando si preme su ricerca date.
     */
@FXML
private void handleRicercaDate() {
	java.util.Date dataInizio=Date.from(dataInizioField.getValue().minusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
	java.util.Date dataFine=Date.from(dataFineField.getValue().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
	ObservableList<MovContoCorrente> filtro = FXCollections.observableArrayList();
	filtro.addAll(mainApp.contoCorrente.GetMovimentiByDates(dataInizio, dataFine));
	conto.setItems(filtro); 
}

    /**
     * Chiamato quando si preme su ricerca categoria.
     */
@FXML
private void handleRicercaDateECategoria() {
	java.util.Date dataInizio=Date.from(dataInizioField.getValue().minusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
	java.util.Date dataFine=Date.from(dataFineField.getValue().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
	ObservableList<MovContoCorrente> filtro = FXCollections.observableArrayList();
	filtro.addAll(mainApp.contoCorrente.GetMovimentiByDatesAndCategory(dataInizio, dataFine, categoriaField.getText()));
	conto.setItems(filtro); 
}

    /**
     * Chiamato quando si preme su ricerca Tag.
     */
@FXML
private void handleRicercaDateETag() {
	java.util.Date dataInizio=Date.from(dataInizioField.getValue().minusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
	java.util.Date dataFine=Date.from(dataFineField.getValue().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
	ObservableList<MovContoCorrente> filtro = FXCollections.observableArrayList();
	filtro.addAll(mainApp.contoCorrente.GetMovimentiByDatesAndTag(dataInizio, dataFine, tagField.getText()));
	conto.setItems(filtro); 
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
