package it.unicam.cs.pa.jbudget105788.project;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.google.gson.Gson;
import it.unicam.cs.pa.jbudget105788.project.Budget;
import it.unicam.cs.pa.jbudget105788.project.ContoCorrente;
import it.unicam.cs.pa.jbudget105788.project.Struttura;

/**
 * La classe Servizi come suggerisce il nome serve per gestire i servizi di lettura e scrittura su un file
 * json nonché di salvare e caricare tutti i dati persistiti
 * 
 * @author Paolo Campanelli 
 *         paolo.campanelli@studenti.unicam.it 105788
 *
 */
public class Servizi {
	
	/**
     * persiste una classe su un file json 
     * 
     * @param elemento
     *                il generico elemento da persistere
     * @param nomeFile
     *                il nome del file dove persistere 
     * @throws IOException
     * 				  IOException
     * 
     */
	public static <T> void Scrivi(T elemento, String nomeFile) throws IOException {
		
		Gson gson = new Gson();	 
		try (FileWriter writer = new FileWriter(nomeFile)) {
	    	gson.toJson(elemento, writer);
	    	} catch (IOException e) {
	    		//Scritttura su log degli errori
	    		throw e;
	    		}
		}
	
	/**
     * legge e trasforma un file json in una classe 
     * 
     * @param tipo
     *                il tipo della classe che verrà creato da quel file
     * @param nomeFile
     *                il nome del file da dove leggere
     * 
     */
	public static <T> T Leggi(Class<T> tipo, String nomeFile) throws IOException {
		
		Gson gson = new Gson();
		 Reader reader = Files.newBufferedReader(Paths.get(nomeFile));
		T classe = gson.fromJson(reader, tipo);
		reader.close();
		return classe;
	}
	
	/**
     * Salva tutto l'ambiente nella giusta sequenza
     * 
     * @param st
     *                la Struttura da persistere
     * @param c
     *                il ContoCorrente da persistere
     * @param b
     *                il Budget da persistere 
     * @param sc
     *                lo Scadenzario da persistere                                    
     * @param path
     *                il percorso della directory dove scrivere
     * @return boolean
     * 				  ritorna true se il salvataggio è andato a buon fine, false altrimenti 
     * @throws
     * 				  IOException                
     * 
     */
	public static boolean Salva(Struttura st, ContoCorrente c, Budget b, Scadenzario sc, String path) throws IOException
	{

		try {
		Scrivi(st,path+"/struttura.json");
		Scrivi(sc,path+"/scadenzario.json");
		Scrivi(c,path+"/contocorrente.json");
		Scrivi(b,path+"/budget.json");
		
	} catch (Exception e)
		{
		return false;
	}
		return true;
	}
	
	/**
     * Carica tutto l'ambiente nella giusta sequenza
     * 
     * @param st
     *                dove inserire la Struttura caricata
     * @param c
     *                dove inserire il ContoCorrente caricato
     * @param b
     *                dove inserire il Budget caricato
     * @param sc
     *                dove inserire lo Scadenzario caricato                        
     * @param path
     *                il percorso della directory da dove leggere
     * @return boolean
     * 				  ritorna true se il caricamento è andato a buon fine, false altrimenti    
     * @throw IOException             
     * 
     */
	public static boolean Carica(Struttura st, ContoCorrente c, Budget b, Scadenzario sc, String path) throws IOException
	{
		
		try {
		Leggi(st.getClass(),path+"/struttura.json");
		Leggi(sc.getClass(),path+"/scadenzario.json");
		Leggi(c.getClass(),path+"/contocorrente.json");
		Leggi(b.getClass(),path+"/budget.json");
		
	} catch (Exception e)
		{
		return false;
	}
		return true;
	}
	
}
