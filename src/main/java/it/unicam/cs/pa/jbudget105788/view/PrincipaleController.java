package it.unicam.cs.pa.jbudget105788.view;

import it.unicam.cs.pa.jbudget105788.application.MainApp;
import javafx.fxml.FXML;

import it.unicam.cs.pa.jbudget105788.project.*;

public class PrincipaleController {
	/**
	 * Classe del controller dell omonimo file FXML
	 *
	 * @author Paolo Campanelli
	 *         paolo.campanelli@studenti.unicam.it 105788
	 *
	 */
	
	// referenza alla main application.
    private MainApp mainApp;

    /**
     * Il costruttore.
     * Il costruttore � chiamato prima del metodo initialize().
     */
    public PrincipaleController() {
    }
    
    /**
     * Inizializza la classe del controllore . uesto metodo � chiamato automaticamente
     * dopo che il file FXML � stato caricato.
     */
    @FXML
    private void initialize() {
    	
    }

    /**
     * metodo chiamato dalla MainApp per dare una referenzazione a se stessa.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

    }
	

	/**
	 * Chiamato quando l'utente preme sul bottone Conto Corrente.
	 */
	@FXML
	private void handleContoCorrente() {
		boolean okClicked = mainApp.showContoCorrente(mainApp.contoCorrente, mainApp.struttura);
	}
	

	/**
	 * Chiamato quando l'utente preme sul bottone Previsioni.
	 */
	@FXML
	private void handleBudget() {
		boolean okClicked = mainApp.showBudget(mainApp.budget, mainApp.struttura);
	}
	
	/**
	 * Chiamato quando l'utente preme sul bottone Categorie.
	 */
	@FXML
	private void handleStruttura() {
		boolean okClicked = mainApp.showStruttura(mainApp.struttura);
	}
	
	/**
	 * Chiamato quando l'utente preme sul bottone Scadenzario.
	 */
	@FXML
	private void handleScadenzario() {
		boolean okClicked = mainApp.showScadenzario(mainApp.scadenzario);
	}

}
