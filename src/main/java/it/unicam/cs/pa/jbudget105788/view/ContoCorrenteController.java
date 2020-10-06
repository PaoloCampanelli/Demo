package it.unicam.cs.pa.jbudget105788.view;

import it.unicam.cs.pa.jbudget105788.application.MainApp;
import it.unicam.cs.pa.jbudget105788.project.ClassificazioneMovimentoEnum;
import it.unicam.cs.pa.jbudget105788.project.ContoCorrente;
import it.unicam.cs.pa.jbudget105788.project.Struttura;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import it.unicam.cs.pa.jbudget105788.project.*;

public class ContoCorrenteController {

    /**
     * Classe del controller dell omonimo file FXML
     *
     * @author Paolo Campanelli
     *         paolo.campanelli@studenti.unicam.it 105788
     *
     */
	@FXML
    private Label attuale;
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


    // Inizializza il conto con 6 colonne.
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

    conto.setItems(mainApp.getContoCorrenteData()); 
    
    if(mainApp.contoCorrente.GetSaldoAttuale() == null)
    	attuale.setText("0");
    else
    	attuale.setText(mainApp.contoCorrente.GetSaldoAttuale().toString());
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
     * Setta il ContoCorrente.
     *
     * @param contoCorrente
     */
public void setContoCorrente(ContoCorrente contoCorrente) {
    this.contoCorrente = contoCorrente;
}

/**
 * Chiamato quando l'utente preme su entrata.
 */
@FXML
private void handleEntrata() {
	mainApp.showNewMovCC(mainApp.contoCorrente, mainApp.struttura, ClassificazioneMovimentoEnum.E );
	if(mainApp.contoCorrente.GetSaldoAttuale() == null)
    	attuale.setText("0");
    else
    	attuale.setText(mainApp.contoCorrente.GetSaldoAttuale().toString());
}

/**
 * Chiamato quando l'utente preme su uscita.
 */
@FXML
private void handleUscita() {
	mainApp.showNewMovCC(mainApp.contoCorrente, mainApp.struttura, ClassificazioneMovimentoEnum.U);
	if(mainApp.contoCorrente.GetSaldoAttuale() == null)
    	attuale.setText("0");
    else
    	attuale.setText(mainApp.contoCorrente.GetSaldoAttuale().toString());
}

/**
 * Chiamato quando l'utente preme su ricerche.
 */
@FXML
private void handleRicerche() {
	mainApp.showRicercheCC(mainApp.contoCorrente, mainApp.struttura);
}

/**
 * Chiamato quando l'utente preme indietro.
 */
@FXML
private void handleBack() {
	initialize();
    dialogStage.close();
}

}
