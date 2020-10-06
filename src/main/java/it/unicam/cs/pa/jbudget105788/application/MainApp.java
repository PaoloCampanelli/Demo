package it.unicam.cs.pa.jbudget105788.application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import it.unicam.cs.pa.jbudget105788.project.*;
import it.unicam.cs.pa.jbudget105788.view.BudgetController;
import it.unicam.cs.pa.jbudget105788.view.ContoCorrenteController;
import it.unicam.cs.pa.jbudget105788.view.NewCategoriaController;
import it.unicam.cs.pa.jbudget105788.view.NewMovBudgetController;
import it.unicam.cs.pa.jbudget105788.view.NewMovCCController;
import it.unicam.cs.pa.jbudget105788.view.NewScadenzaController;
import it.unicam.cs.pa.jbudget105788.view.PrimaVoltaController;
import it.unicam.cs.pa.jbudget105788.view.PrincipaleController;
import it.unicam.cs.pa.jbudget105788.view.RicercheBDController;
import it.unicam.cs.pa.jbudget105788.view.RicercheCCController;
import it.unicam.cs.pa.jbudget105788.view.ScadenzarioController;
import it.unicam.cs.pa.jbudget105788.view.StrutturaController;
/**
 * La classe MainApp è la classe contenente il main e l'inizzializzazione
 * dell' interfaccia grafica
 * 
 * @author Paolo Campanelli 
 *         paolo.campanelli@studenti.unicam.it 105788
 *
 */

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    
    //strutturaData, scadenzarioData, contoCorrenteData e budgetData sono delle observable list
    //delle rispettive classi
    public ObservableList<Categoria> strutturaData = FXCollections.observableArrayList();
    public ObservableList<Scadenza> scadenzarioData = FXCollections.observableArrayList();
    public ObservableList<MovContoCorrente> contoCorrenteData = FXCollections.observableArrayList();
    public ObservableList<MovBudget> budgetData = FXCollections.observableArrayList();

    public Struttura struttura;
    public ContoCorrente contoCorrente;
    public Budget budget;
    public Scadenzario scadenzario; 
    public String path;
	
	public MainApp() throws IOException {
		
	}
	
	public ObservableList<Categoria> getStrutturaData() {
		return strutturaData;
		}
	
	public ObservableList<Scadenza> getScadenzarioData() {
		return scadenzarioData;
		}
	
	public ObservableList<MovContoCorrente> getContoCorrenteData() {
		return contoCorrenteData;
		}
	
	public ObservableList<MovBudget> getBudgetData() {
		return budgetData;
		}
	
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("jbudget 105788");

        initRootLayout();
        
        SettaAmbiente();

        scadenzario.SetPagata(contoCorrente);

        showPrincipaleOverview();
    }
    
    /**
     * Inizializza il root layout. 
     * @throws IOException 
     */
    
    private void SettaAmbiente() throws IOException
    {
    	File conf = new File(".\\src\\main\\resources\\config.properties");
    	Properties prop = new Properties();
    	if (!conf.isFile())
    	{
    		conf.createNewFile();
    	}
    	
         // load a properties file
         prop.load(new FileInputStream(".\\src\\main\\resources\\config.properties"));

         // get the property value and print it out 
         path = prop.getProperty("pathFile");
         try {
             if (path == null) {
                 path = showPrimaVolta();
             }

             struttura = Servizi.Leggi(Struttura.class, path + "/struttura.json");
             strutturaData.addAll(struttura.GetCategorie());

             scadenzario = Servizi.Leggi(Scadenzario.class, path + "/scadenzario.json");
             scadenzarioData.addAll(scadenzario.GetScadenze());

             contoCorrente = Servizi.Leggi(ContoCorrente.class, path + "/contocorrente.json");
             contoCorrenteData.addAll(contoCorrente.GetMovContoCorrente());

             budget = Servizi.Leggi(Budget.class, path + "/budget.json");
             budgetData.addAll(budget.GetMovBudget());
         }
            catch(Exception e){
             System.exit(0);
            };
        prop.setProperty("pathFile", path);
        prop.store(new FileOutputStream(".\\src\\main\\resources\\config.properties"), null);
        Servizi.Salva(new Struttura(), new ContoCorrente(), new Budget(), new Scadenzario(), path);
     	return;
    }
    
    /**
     * Inizzializza il root layout.
     */
    public void initRootLayout() {
        try {
            // Carica il root layout da un fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // mostra la scene contenente il root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }  
    
    
    public String showPrimaVolta() {
        try {
        	// Carica il file FXML e vrea un nuovo stage per il popup di dialog
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/PrimaVolta.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // crea ol dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Files");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Setta la struttura nel controller.
            PrimaVoltaController primaVolta = loader.getController();
            primaVolta.setDialogStage(this,dialogStage);

            // Mostra il dialogo e aspetta che l'utente lo chiuda
            dialogStage.showAndWait();
            return primaVolta.getPathDir();

        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Mostra il PrincipaleOverview all' interno dell root layout
     */
    public void showPrincipaleOverview() {
        try {
        	// Carica PrincipaleOverview
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/Principale.fxml"));
            AnchorPane categoriaOverview = (AnchorPane) loader.load();
            
            // Setta PrincipaleOverview al centro dell root layout.
            rootLayout.setCenter(categoriaOverview);
            
            // da al controller l' accesso a main app.
	        PrincipaleController controller = loader.getController();
	        controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
	/**
	 * ritorna il main stage.
	 * @return primaryStage
	 * 				ritorna il primaryStage	
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	
    public boolean showStruttura(Struttura struttura) {
        try {
        	// Carica il file FXML e vrea un nuovo stage per il popup di dialog
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/Struttura.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // crea ol dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Categorie");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Setta la struttura nel controller.
            StrutturaController StrutturaC = loader.getController();
            StrutturaC.setDialogStage(this,dialogStage);

            // Mostra il dialogo e aspetta che l'utente lo chiuda
            dialogStage.showAndWait();

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean showNewCategoria(Struttura struttura) {
    	  try {
    		  
              FXMLLoader loader = new FXMLLoader();
              loader.setLocation(MainApp.class.getResource("/NewCategoria.fxml"));
              AnchorPane page = (AnchorPane) loader.load();

              Stage dialogStage = new Stage();
              dialogStage.setTitle("Aggiungi Categoria");
              dialogStage.initModality(Modality.WINDOW_MODAL);
              dialogStage.initOwner(primaryStage);
              Scene scene = new Scene(page);
              dialogStage.setScene(scene);
              
              NewCategoriaController controller = loader.getController();
              controller.setDialogStage(dialogStage);
              controller.setCategoria(struttura);
              controller.setMainApp(this);

              dialogStage.showAndWait();

              return controller.isOkClicked();
          } catch (IOException e) {
              e.printStackTrace();
              return false;
          }
    }
    
    public boolean showContoCorrente(ContoCorrente contoCorrente,Struttura struttura) {
    	
        	try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainApp.class.getResource("/ContoCorrente.fxml"));
                AnchorPane page = (AnchorPane) loader.load();

                Stage dialogStage = new Stage();
                dialogStage.setTitle("Conto Corrente");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(primaryStage);
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);

                ContoCorrenteController ContoCorrenteC = loader.getController();
                ContoCorrenteC.setDialogStage(this,dialogStage);

                dialogStage.showAndWait();

                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        
    }
    
    public boolean showNewMovCC(ContoCorrente contoCorrente,Struttura struttura, ClassificazioneMovimentoEnum classificazioneMovimento) {
  	  try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/NewMovCC.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Aggiungi Movimento");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            NewMovCCController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setCategoria(struttura);
            controller.setContoCorrente(contoCorrente);
            controller.setClassificazioneMovimento(classificazioneMovimento);
            controller.setMainApp(this);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
  	  
  	  
  }
    
    public boolean showRicercheCC(ContoCorrente contoCorrente,Struttura struttura) {
    	  try {
              FXMLLoader loader = new FXMLLoader();
              loader.setLocation(MainApp.class.getResource("/RicercheCC.fxml"));
              AnchorPane page = (AnchorPane) loader.load();

              Stage dialogStage = new Stage();
              dialogStage.setTitle("Ricerca Movimento");
              dialogStage.initModality(Modality.WINDOW_MODAL);
              dialogStage.initOwner(primaryStage);
              Scene scene = new Scene(page);
              dialogStage.setScene(scene);

              RicercheCCController controller = loader.getController();
              controller.setDialogStage(this,dialogStage);
              controller.setCategoria(struttura);
              controller.setContoCorrente(contoCorrente);

              dialogStage.showAndWait();

              return true;
          } catch (IOException e) {
              e.printStackTrace();
              return false;
          }
    }
    
    
    public boolean showBudget(Budget budget,Struttura struttura) {
    	
    	try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/Budget.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Previsioni");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            BudgetController BudgetC = loader.getController();
            BudgetC.setDialogStage(this,dialogStage);

            dialogStage.showAndWait();

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    
    }
    
    public boolean showNewMovBudget(Budget budget,Struttura struttura, ClassificazioneMovimentoEnum classificazioneMovimento) {
    	  try {
              FXMLLoader loader = new FXMLLoader();
              loader.setLocation(MainApp.class.getResource("/NewMovBudget.fxml"));
              AnchorPane page = (AnchorPane) loader.load();

              Stage dialogStage = new Stage();
              dialogStage.setTitle("Aggiungi Movimento");
              dialogStage.initModality(Modality.WINDOW_MODAL);
              dialogStage.initOwner(primaryStage);
              Scene scene = new Scene(page);
              dialogStage.setScene(scene);

              NewMovBudgetController controller = loader.getController();
              controller.setDialogStage(dialogStage);
              controller.setCategoria(struttura);
              controller.setBudget(budget);
              controller.setClassificazioneMovimento(classificazioneMovimento);
              controller.setMainApp(this);

              dialogStage.showAndWait();

              return controller.isOkClicked();
          } catch (IOException e) {
              e.printStackTrace();
              return false;
          }
    	  
    	  
    }
    
    public boolean showRicercheBD(Budget budget,Struttura struttura) {
  	  try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/RicercheBD.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Ricerca Previsione");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            RicercheBDController controller = loader.getController();
            controller.setDialogStage(this,dialogStage);
            controller.setCategoria(struttura);
            controller.setBudget(budget);

            dialogStage.showAndWait();

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
  }

    public boolean showScadenzario(Scadenzario scadenzario) {
    	
    	try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/Scadenzario.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Scadenzario");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ScadenzarioController scadenzarioC = loader.getController();
            scadenzarioC.setDialogStage(this,dialogStage);

            dialogStage.showAndWait();

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    
}
    
    public boolean showNewScadenza(Scadenzario scadenzario) {
  	  try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/NewScadenza.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Aggiungi Scadenza");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            NewScadenzaController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setScadenzario(scadenzario);
            controller.setMainApp(this);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
  	  
  	  
  }
    
    

    @Override
	public void stop() throws Exception {

    	Servizi.Salva(struttura, contoCorrente, budget, scadenzario, path);
		super.stop();
	}

	public static void main(String[] args) {

        launch(args);
    }
}
