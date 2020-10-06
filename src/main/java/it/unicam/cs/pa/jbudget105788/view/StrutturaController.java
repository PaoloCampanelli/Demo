package it.unicam.cs.pa.jbudget105788.view;

import it.unicam.cs.pa.jbudget105788.application.MainApp;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import it.unicam.cs.pa.jbudget105788.project.*;


public class StrutturaController {
    /**
     * Classe del controller dell omonimo file FXML
     *
     * @author Paolo Campanelli
     *         paolo.campanelli@studenti.unicam.it 105788
     *
     */

		@FXML
	    private TableView<Categoria> Categoria;
	    @FXML
	    private TableColumn<Categoria, String> codice;
	    @FXML
	    private TableColumn<Categoria, String> descrizione;
	    @FXML
	    private TableColumn<Categoria, String> livello;

	    private MainApp mainApp;


    private Stage dialogStage;
    /**
     * Inizializza la classe del controllore . questo metodo � chiamato automaticamente
     * dopo che il file FXML � stato caricato.
     */
    @FXML
    private void initialize() {
    	
    	
    	// Initialize the person table with the two columns.
    	codice.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().Codice));
    	descrizione.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().Descrizione));
    	livello.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().Livello.toString()));


    }

    /**
     * Setta lo stage di questa classe.
     *
     * @param dialogStage
     * @param mainApp
     */
    public void setDialogStage(MainApp mainApp, Stage dialogStage) {
        this.dialogStage = dialogStage;
        this.mainApp = mainApp;

        Categoria.setItems(mainApp.getStrutturaData());
    }

    /**
     * Chiamato quando l'utente preme su nuova.
     */
    @FXML
    private void handleNew() {mainApp.showNewCategoria(mainApp.struttura);}

    /**
     * Chiamato quando l'utente preme indietro.
     */
    @FXML
    private void handleBack() {
    	initialize();
        dialogStage.close();
    }

    
}
