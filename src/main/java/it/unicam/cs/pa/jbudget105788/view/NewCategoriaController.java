package it.unicam.cs.pa.jbudget105788.view;

import it.unicam.cs.pa.jbudget105788.application.MainApp;
import it.unicam.cs.pa.jbudget105788.project.Struttura;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import it.unicam.cs.pa.jbudget105788.project.*;

public class NewCategoriaController {
	/**
	 * Classe del controller dell omonimo file FXML
	 *
	 * @author Paolo Campanelli
	 *         paolo.campanelli@studenti.unicam.it 105788
	 *
	 */

		@FXML
	    private TextField codiceField;
	    @FXML
	    private TextField descrizioneField;
	  
	    private Stage dialogStage;
	    private Struttura struttura;
	    private boolean okClicked = false;
	    private MainApp mainApp;
	/**
	 * Inizializza la classe del controllore . questo metodo è chiamato automaticamente
	 * dopo che il file FXML è stato caricato.
	 */
	    @FXML
	    private void initialize(){ }

	/**
	 * Setta la mainApp di questa classe.
	 *
	 * @param mainApp
	 */
	    public void setMainApp(MainApp mainApp) {
	        this.mainApp = mainApp;

	    }

	/**
	 * Setta lo stage di questa classe.
	 *
	 * @param dialogStage
	 */
	    public void setDialogStage(Stage dialogStage) {
	        this.dialogStage = dialogStage;
	    }

	/**
	 * Setta la struttura.
	 *
	 * @param struttura
	 */
	    public void setCategoria(Struttura struttura) {
	        this.struttura = struttura;

	        codiceField.setText("");
	        descrizioneField.setText("");
	    
	    }

	/**
	 * Vede se Ok è premuto.
	 */
	    public boolean isOkClicked() {
	        return okClicked;
	    }

	    /**
	     * Chiamato quando l'utente preme su ok.
	     */
	    @FXML
	    private void handleOk() {
	    	String message = "";
	    	Categoria retCategoria = null;
	    	String cod = codiceField.getText();
	    	String des = descrizioneField.getText();
	    	try {
	    		retCategoria = struttura.CreaLivello(cod, des);
	    		mainApp.strutturaData.add(retCategoria);
	    		message += "Categoria "+retCategoria.toString()+" aggiunta!!!";
		    	Alert alert = new Alert(AlertType.INFORMATION);
	            alert.initOwner(dialogStage);
	            alert.setTitle("Risultato");
	            alert.setHeaderText("Headretext");
	            alert.setContentText(message);

	            alert.showAndWait();
	    	} catch (Exception e) {
	    		message += "CODICE ERRATO!\n"; 
	    		Alert alert = new Alert(AlertType.ERROR);
	            alert.initOwner(dialogStage);
	            alert.setTitle("Invalid Fields");
	            alert.setHeaderText("Please correct invalid fields");
	            alert.setContentText(message);
	            
	            alert.showAndWait();
	    	}
	    	
	    	
	    	dialogStage.close();

	    }

	    /**
	     * Chiamato quando l'utente preme su cancel.
	     */
	    @FXML
	    private void handleCancel() {
	        dialogStage.close();
	    }

	   
}
