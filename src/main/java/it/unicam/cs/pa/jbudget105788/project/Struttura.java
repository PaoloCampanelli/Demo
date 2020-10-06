package it.unicam.cs.pa.jbudget105788.project;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Un oggetto della classe Struttura rappresenta un insieme di categorie
 * 
 * @author Paolo Campanelli 
 *         paolo.campanelli@studenti.unicam.it 105788
 *
 */
public class Struttura implements ICreaLivelloCategoria {


	//L'insieme delle categorie
    public List<Categoria> Categorie;
    
  //Costruisce un certo set di Categoria vuoto
    public Struttura() {
        Categorie = new ArrayList<Categoria>() {
        };
    }

    /**
     *Crea il livello della struttura richiesto. Verifica che non esista gi� ma che esista il nodo padre che lo deve contenere.
     * 
     * @param codice
     *                Il codice da creare, di lunghezza 3, 6 o 9
     * @param descrizione
     *                La descrzione della categoria 
     * 
     * @throws IllegalArgumentException
     *                                  se la lunghezza del codice non � giusta
     */
    public Categoria CreaLivello(String codice, String descrizione)
    {
        
        //Verifico la lunghezza del codice
        if(codice.length() != 3 && codice.length() != 6 && codice.length() != 9)
            throw new IllegalArgumentException("Il codice passato deve essere di lunghezza 3, 6 o 9 cartatteri.");

        //Verifico nel caso il codice fosse presente, che anche la descrizione coincida
        if (Categorie.stream().anyMatch(p -> p.Codice.equals(codice) && !p.Descrizione.equals(descrizione)))
        throw new IllegalArgumentException("Il codice passato � presente ma con una descrizione diversa.");

        //Verifico il caso che la categoria esista gi� perfettamente uguale, nel caso ritorna la stessa
        if (Categorie.stream().anyMatch(p -> p.Codice.equals(codice) && p.Descrizione.equals(descrizione)))
        return Categorie.stream().filter(p -> p.Codice.equals(codice)).findFirst().orElse(null);

        // Il codice passato non � stato trovato e rispetta le regole sintattiche richieste
        String padre = Categoria.RitornaPadre(codice);

        //E' un root oppure esite la struttura per contenenerlo, posso sicuramente crearlo.
        if (padre == null || Categorie.stream().anyMatch(p -> p.Codice.equals(padre))) {
        	Categoria cate = new Categoria().CreaLivello(codice, descrizione);
        	Categorie.add(cate);
        	  return cate;
        	
        }
      
            else
        throw new IllegalArgumentException("Non esiste la struttura " + padre + " per ospitare il codice passato.");
    }

    /**
     *Ritorna la categoria associata ad un codice
     * 
     * @param codice
     *                Il codice con cui cercare la categoria, di lunghezza 3, 6 o 9     
     * @return Categoria
     * 				  ritorna la categoria con quel codice 
     * 
     */
    public Categoria GetCategoriaByCodice(String codice)
    {
    	return Categorie.stream().filter(p->p.Codice.equals(codice)).findFirst().orElse(null);
    }
    
    /**
     *Ritorna tutte le categorie
     * 
     * @return List<Categoria>
     * 				  ritorna tutte le categorie
     * 
     */
	public List<Categoria> GetCategorie() {
		return Categorie.stream().collect(Collectors.toList());
	}
	
    @Override
	public String toString() {
		return "Struttura [Categorie=" + Categorie + "]";
	}
    
}
