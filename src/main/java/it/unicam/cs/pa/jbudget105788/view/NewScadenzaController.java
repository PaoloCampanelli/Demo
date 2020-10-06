package it.unicam.cs.pa.jbudget105788.view;

import it.unicam.cs.pa.jbudget105788.application.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.unicam.cs.pa.jbudget105788.application.*;
import it.unicam.cs.pa.jbudget105788.project.*;

public class NewScadenzaController {
    /**
     * Classe del controller dell omonimo file FXML
     *
     * @author Paolo Campanelli
     *         paolo.campanelli@studenti.unicam.it 105788
     *
     */

	@FXML
    private TextField importoField;
	@FXML
    private TextField interessiField;
    @FXML
    private DatePicker dataField;   
    @FXML
    private TextField tagsField;
    @FXML
    private TextField descrizioneField;
  
    private Stage dialogStage;
    private Scadenzario scadenzario;
    private boolean okClicked = false;
    private MainApp mainApp;
    private ScadenzarioController scadenzarioController;


    /**
     * Inizializza la classe del controllore . questo metodo è chiamato automaticamente
     * dopo che il file FXML è stato caricato.
     */
    @FXML
    private void initialize() {
        dataField.setValue(LocalDate.now());
    }

    /**
     * Setta il dialogStage di questa classe.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Setta la mainApp di questa classe.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }


    /**
     * Setta lo scadenzario.
     *
     * @param scadenzario
     */
	public void setScadenzario(Scadenzario scadenzario) {
		this.scadenzario = scadenzario;
		importoField.setText("");
	    interessiField.setText("0");
	    tagsField.setText("");
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
    private void handleOk() throws ParseException{
        if (isInputValid()) {
        	BigDecimal importo = new BigDecimal(importoField.getText());
        	BigDecimal interessi = new BigDecimal(interessiField.getText());
        	java.util.Date data=Date.from(dataField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        	List<String> tags = new ArrayList<String>();
        	tags.add(tagsField.getText());
        	String descrizione = descrizioneField.getText();
        	Scadenza retScadenza;
        	if(interessi.equals(BigDecimal.ZERO))
        		retScadenza = mainApp.scadenzario.CreaScadenza(importo, data, descrizione, tags);
        	else
        		retScadenza = mainApp.scadenzario.CreaScadenza(importo, interessi, data, descrizione, tags);
    		mainApp.scadenzarioData.add(retScadenza);
    		String message = "Movimento "+retScadenza.toString()+" aggiunto!!!";
	    	Alert alert = new Alert(AlertType.INFORMATION);
            alert.initOwner(dialogStage);
            alert.setTitle("Risultato");
            alert.setHeaderText("Headretext");
            alert.setContentText(message);
            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Chiamato quando l'utente preme su indietro.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }


    /**
     * Valida la correttezza del input
     *
     * @return true se l'imput è valido
     */
    private boolean isInputValid() {
    	
    	String errorMessage = "";
        
        String imp = new String();
        imp = importoField.getText();   
        if (!imp.matches("\\d+(\\.\\d{2})?|\\.\\d{2}")) 
            errorMessage += "NON E' UN NUMER0!\n"; 
         
        String inte = new String();
        inte = interessiField.getText();
        if (!inte.matches("\\d+(\\.\\d{2})?|\\.\\d{2}")) 
            errorMessage += "NON E' UN NUMER0!\n"; 
		
        java.util.Date data=Date.from(dataField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        if(data.before(Date.from(Instant.now())))
        	errorMessage += "LA DATA DEVE ESSERE NEL FUTURO!\n"; 
        	
	

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            
            alert.showAndWait();
            
            return false;
        }
    }
	
}
