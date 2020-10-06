package it.unicam.cs.pa.jbudget105788.view;

import it.unicam.cs.pa.jbudget105788.application.MainApp;
import it.unicam.cs.pa.jbudget105788.project.Budget;
import it.unicam.cs.pa.jbudget105788.project.ClassificazioneMovimentoEnum;
import it.unicam.cs.pa.jbudget105788.project.MovBudget;
import it.unicam.cs.pa.jbudget105788.project.Struttura;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import it.unicam.cs.pa.jbudget105788.project.*;


public class NewMovBudgetController {

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
    private Budget budget;
    private boolean okClicked = false;
    private MainApp mainApp;
    private Budget BudgetController;
	private Struttura struttura;
	private ClassificazioneMovimentoEnum classificazioneMovimento;

    /**
     * Inizializza la classe del controllore . questo metodo è chiamato automaticamente
     * dopo che il file FXML è stato caricato.
     */
    @FXML
    private void initialize() {
    	
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
     * Seta La classificazione del movimento.
     * 
     * @param classificazioneMovimento
     */
    public void setClassificazioneMovimento(ClassificazioneMovimentoEnum classificazioneMovimento) {
        this.classificazioneMovimento = classificazioneMovimento;   
    }

    /**
     * Setta il budget.
     *
     * @param budget
     */
	public void setBudget(Budget budget) {
		this.budget = budget;
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
        	List<String> tags = new ArrayList<String>();
        	tags.add(tagsField.getText());
        	Categoria categoria = mainApp.struttura.GetCategoriaByCodice(categoriaField.getText());
        	MovBudget retMov;
        	if(classificazioneMovimento == ClassificazioneMovimentoEnum.E)
        		retMov = mainApp.budget.CreaEntrata(importo, data, causale, tags, categoria);
        	else
        		retMov = mainApp.budget.CreaUscita(importo, data, causale, tags, categoria);
    		mainApp.budgetData.add(retMov);
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
