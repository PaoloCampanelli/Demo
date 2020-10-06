package it.unicam.cs.pa.jbudget105788.view;

import it.unicam.cs.pa.jbudget105788.application.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;

import it.unicam.cs.pa.jbudget105788.project.*;

public class PrimaVoltaController {
	/**
	 * Classe del controller dell omonimo file FXML
	 *
	 * @author Paolo Campanelli
	 *         paolo.campanelli@studenti.unicam.it 105788
	 *
	 */

	private String pathDir;
	
	@FXML
	private MainApp mainApp;
	@FXML
	private Stage dialogStage;
	@FXML
	private TextField pathField;

	/**
	 * Inizializza la classe del controllore . questo metodo è chiamato automaticamente
	 * dopo che il file FXML è stato caricato.
	 */
	@FXML
	public void initialize() {

	}

	/**
	 * Costruttore della Classe
	 */
	public PrimaVoltaController() {
	
	}

	/**
	 * Setta il dialogStage di questa classe.
	 *
	 * @param dialogStage
	 */
    public void setDialogStage(MainApp mainApp, Stage dialogStage) {
        this.dialogStage = dialogStage;
        this.mainApp = mainApp;

    }
    
    public void setPrimaVolta() {
        pathField.setText("");
    }
    
    public void setPathDir(String dir)
    {
    	pathDir = dir;
    }
    
    public String getPathDir()
    {
    	return pathDir;
    }


	/**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

	@FXML
	private void handleButton() throws IOException {
		//Creo la cartella
		String path = pathField.getText();
		/*//precondizione: il path non deve essere vuoto
		if(path.equals(""))
			System.exit(0);*/
		File directory = new File(path);
	    if (! directory.exists()){
	        directory.mkdirs();
	    }
	    setPathDir(path);
	    dialogStage.close();
		
	}

}
