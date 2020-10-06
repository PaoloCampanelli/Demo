package it.unicam.cs.pa.jbudget105788.view;

import it.unicam.cs.pa.jbudget105788.application.MainApp;
import it.unicam.cs.pa.jbudget105788.project.ClassificazioneMovimentoEnum;
import it.unicam.cs.pa.jbudget105788.project.ContoCorrente;
import it.unicam.cs.pa.jbudget105788.project.Struttura;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import it.unicam.cs.pa.jbudget105788.project.*;


public class NewMovCCController {
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
    private DatePicker dataField;
    @FXML
    private TextField causaleField;
    @FXML
    private TextField tagsField;
    @FXML
    private TextField categoriaField;
  
    private Stage dialogStage;
    private ContoCorrente contoCorrente;
    private boolean okClicked = false;
    private MainApp mainApp;
    private ContoCorrenteController CCController;
	private Struttura struttura;
	private ClassificazioneMovimentoEnum classificazioneMovimento;

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
     * Setta la struttura.
     *
     * @param struttura
     */
    public void setCategoria(Struttura struttura) {
        this.struttura = struttura;   
    }

    /**
     * Setta La classificazione del movimento.
     *
     * @param classificazioneMovimento
     */
    public void setClassificazioneMovimento(ClassificazioneMovimentoEnum classificazioneMovimento) {
        this.classificazioneMovimento = classificazioneMovimento;   
    }

    /**
     * Setta il contoCorrente.
     *
     * @param contoCorrente
     */
	public void setContoCorrente(ContoCorrente contoCorrente) {
		this.contoCorrente = contoCorrente;
		importoField.setText("");
	    causaleField.setText("");
	    tagsField.setText("");
	    categoriaField.setText("");
		
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
        	java.util.Date data=Date.from(dataField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        	String causale = causaleField.getText();
        	List<String> tags = Arrays.asList(tagsField.getText().split(","));
        	Categoria categoria = mainApp.struttura.GetCategoriaByCodice(categoriaField.getText());
        	MovContoCorrente retMov;
        	if(classificazioneMovimento == ClassificazioneMovimentoEnum.E)
        		retMov = mainApp.contoCorrente.CreaEntrata(importo, data, causale, tags, categoria);
        	else
        		retMov = mainApp.contoCorrente.CreaUscita(importo, data, causale, tags, categoria);
    		mainApp.contoCorrenteData.add(retMov);
    		String message = "Movimento "+retMov.toString()+" aggiunto!!!";
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
     * Chiamato quando l'utente preme su visualizza.
     */
    @FXML
    private void handleCategoria() {
    	mainApp.showStruttura(mainApp.struttura);
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
         
		
		String codice = categoriaField.getText();
        if(mainApp.struttura.GetCategoriaByCodice(codice) == null)
        	errorMessage += "NON E' UNA CATEGORIA VALIDA!\n"; 
	

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
