package it.unicam.cs.pa.jbudget105788.project;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Un oggetto della classe Scadenzario rappresenta un insieme di Scadenze oltre ai metodi per gestirle
 * 
 * @author Paolo Campanelli 
 *         paolo.campanelli@studenti.unicam.it 105788
 *
 */
public class Scadenzario implements ICreaScadenza {
	
	 //L'insieme delle Scadenze
	 public List<Scadenza> Scadenze;
	
	 //Costruisce un certo set di Scadenze vuoto
	 public Scadenzario() {
	        Scadenze = new ArrayList<Scadenza>() {
	        };
	    }

	 /**
	     * aggiunge una scadenza ordinaria allo Scadenzario
	     * 
	     * @param Importo
	     *                l'importo da aggiungere
	     * @param Data
	     *                la data da aggiungere 
	     * @param Descrizione
	     *                la descrizione da aggiungere   
	     * @param tags
	     *                la lista di tag da aggiungere          
	     * @return e
	     * 				  ritorna la scadenza aggiunta
	     * 
	     */
	public Scadenza CreaScadenza(BigDecimal Importo, Date Data, String Descrizione, List<String> tags) {
		 if (Importo.compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("L'importo deve essere maggiore di 0.");
         if (Data.before(Date.from(Instant.now()))) throw new IllegalArgumentException("La data della scadenza deve essere nel futuro");
         Scadenza e = new Scadenza().CreaScadenza(Importo, Data, Descrizione, tags);
         int maxId = 0;
         for(Scadenza movimento : Scadenze ){
             if(movimento.Id > maxId)
                 maxId = movimento.Id;
         }
         e.Id = maxId+1;
         e.setIsPagata(false);
         this.Scadenze.add(e);
         return e;
	}


	/**
     *Aggiunge una scadenza di un prestito, separando la quota capitale da quella interessi
     *e la aggiunge allo Scadenzario
     * 
     * @param importoCapitale
     *                l'importo effettivo da aggiungere
     * @param importoInteressi
     *                l'importo degli interessi da aggiungere
     * @param data
     *                la data da aggiungere 
     * @param descrizione
     *                la descrizione da aggiungere   
     * @param tags
     *                la lista di tag da aggiungere          
     * @return e
     * 				  ritorna la scadenza aggiunta
     * 
     */
	public Scadenza CreaScadenza(BigDecimal importoCapitale, BigDecimal importoInteressi, Date data,String descrizione,List<String> tags) {
		if (importoCapitale.compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("L'importo capitale deve essere maggiore di 0.");
		if (importoInteressi.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("L'importo degli interessi deve essere maggiore o uguale a 0.");
        if (data.before(Date.from(Instant.now()))) throw new IllegalArgumentException("La data della scadenza deve essere nel futuro");
        Scadenza e = new Scadenza().CreaScadenza(importoCapitale,importoInteressi, data, descrizione, tags);
        int maxId = 0;
        for(Scadenza movimento : Scadenze ){
            if(movimento.Id > maxId)
                maxId = movimento.Id;
        }
        e.Id = maxId+1;
        e.setIsPagata(false);
        this.Scadenze.add(e);
        return e;
	}

	/**
     * Controlla se ogni scadenza dello Scadenzario ha raggiunto la data per essere pagata con 
     * l'ononimo metodo di Scadenza 
     * 
     * @param cc
     *                Il conto da cui scalare le scadenze        
     * 
     */
	public void SetPagata(ContoCorrente cc) {
		 for (Scadenza scadenza : Scadenze) {
			 scadenza.SetPagata(cc);			
		}
		
	}
	
	/**
     *Ritorna tutte le scadenze
     * 
     * @return Scadenze
     * 				  ritorna tutte le scadenze
     * 
     */
	public List<Scadenza> GetScadenze() {
		return Scadenze.stream().collect(Collectors.toList());
	}

	/**
	 *Ritorna tutte le scadenze non pagate
	 *
	 * @return Scadenze
	 * 				  ritorna tutte le scadenze non pagate
	 *
	 */
	public List<Scadenza> GetScadenzeNonPagate() {
		return GetScadenze().stream().filter(a->a.getIsPagata()==false).collect(Collectors.toList());
	}

	@Override
	public String toString() {
		return "Scadenzario [Scadenze=" + Scadenze + "]";
	}
	
	

}
