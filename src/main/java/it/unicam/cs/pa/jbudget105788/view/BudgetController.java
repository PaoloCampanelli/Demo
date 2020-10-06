package it.unicam.cs.pa.jbudget105788.view;

import it.unicam.cs.pa.jbudget105788.application.MainApp;
import it.unicam.cs.pa.jbudget105788.project.Budget;
import it.unicam.cs.pa.jbudget105788.project.ClassificazioneMovimentoEnum;
import it.unicam.cs.pa.jbudget105788.project.MovBudget;
import it.unicam.cs.pa.jbudget105788.project.Struttura;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import it.unicam.cs.pa.jbudget105788.project.*;

public class BudgetController {
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
    private TableView<MovBudget> budgetTable;
    @FXML
    private TableColumn<MovBudget, String> id;
    @FXML
    private TableColumn<MovBudget, String> importo;
    @FXML
    private TableColumn<MovBudget, String> data;
    @FXML
    private TableColumn<MovBudget, String> causale;
    @FXML
    private TableColumn<MovBudget, String> tags;
    @FXML
    private TableColumn<MovBudget, String> categoria;

    private MainApp mainApp;
    private Stage dialogStage;
    private Struttura struttura;
    private Budget budget;
    /**
     * Inizializza la classe del controllore. questo metodo è chiamato automaticamente
     * dopo che il file FXML è stato caricato.
     */
@FXML
private void initialize() {
	
	
	// Inizializza le previsioni con 6 colonne.
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

    budgetTable.setItems(mainApp.getBudgetData()); 
    if(mainApp.budget.GetSaldoAttuale() == null)
    	attuale.setText("0");
    else
    	attuale.setText(mainApp.budget.GetSaldoAttuale().toString());
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
 * Setta il budget.
 * 
 * @param budget
 */
public void setBudget(Budget budget) {
    this.budget = budget;
}

/**
 * Chiamato quando l'utente preme su entrata.
 */
@FXML
private void handleEntrata() {
	mainApp.showNewMovBudget(mainApp.budget, mainApp.struttura, ClassificazioneMovimentoEnum.E );
	if(mainApp.budget.GetSaldoAttuale() == null)
    	attuale.setText("0");
    else
    	attuale.setText(mainApp.budget.GetSaldoAttuale().toString());
}

/**
 * Chiamato quando l'utente preme su uscita.
 */
@FXML
private void handleUscita() {
	mainApp.showNewMovBudget(mainApp.budget, mainApp.struttura, ClassificazioneMovimentoEnum.U);
	if(mainApp.budget.GetSaldoAttuale() == null)
    	attuale.setText("0");
    else
    	attuale.setText(mainApp.budget.GetSaldoAttuale().toString());
}

/**
* Chiamato quando l'utente preme su ricerche.
*/
@FXML
private void handleRicerche() {
	mainApp.showRicercheBD(mainApp.budget, mainApp.struttura);
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
