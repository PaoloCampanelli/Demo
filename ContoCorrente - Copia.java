package it.unicam.cs.pa.jbudget105788.project;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;


public class ContoCorrente implements ICreaMovimento<MovContoCorrente>, IGetStatistiche<MovContoCorrente>{

	/**
	 * Un oggetto della classe ContoCorrente rappresenta un conto reale usato per movimenti reali
	 * 
	 * @author Paolo Campanelli 
	 *         paolo.campanelli@studenti.unicam.it 105788
	 *
	 */
	//L'insieme dei movimenti
    public List<MovContoCorrente> Movimenti;

  //Costruisce un certo set di MovContoCorrente vuoto
    public ContoCorrente() {
        Movimenti = new ArrayList<MovContoCorrente>() {
        };
    }

    /**
     * Aggiunge un MovContoCorrente positivo(un'entrata) a questo conto.
     * 
     * @param importo
     *                l'importo da aggiungere
     * @param data
     *                la data da aggiungere 
     * @param causale
     *                la causale da aggiungere   
     * @param tags
     *                la lista di tag da aggiungere        
     * @param categoria
     *                la categoria da aggiungere    
     * @return MovBudget
     * 				  ritorna il movimento 
     * 
     * @throws IllegalArgumentException
     *                                  se l' importo è minore di 0
     */
    public MovContoCorrente CreaEntrata(BigDecimal importo, Date data, String causale, List<String> tags, Categoria categoria) {
        if(importo.compareTo(BigDecimal.ZERO) > 0){
            MovContoCorrente e = new MovContoCorrente().CreaEntrata(importo, data, causale, tags, categoria);
            int maxId = 0;
          //ciclo per assegnare sempre un id diverso
            for(MovContoCorrente movimento : Movimenti ){
                if(movimento.Id > maxId)
                    maxId = movimento.Id;
            }
            e.Id = maxId+1;
            this.Movimenti.add(e);
            return e;
        }
        throw new IllegalArgumentException("L'importo deve essere maggiore di 0.");
    }

    /**
     * Aggiunge un MovContoCorrente negativo(un'uscita) a questo conto.
     * 
     * @param importo
     *                l'importo da aggiungere
     * @param data
     *                la data da aggiungere 
     * @param causale
     *                la causale da aggiungere   
     * @param tags
     *                la lista di tag da aggiungere        
     * @param categoria
     *                la categoria da aggiungere    
     * @return MovBudget
     * 				  ritorna il movimento 
     * 
     * @throws IllegalArgumentException
     *                                  se l' importo è minore di 0
     */
    public MovContoCorrente CreaUscita(BigDecimal importo, Date data, String causale, List<String> tags, Categoria categoria) {
        if(importo.compareTo(BigDecimal.ZERO)>0){
            MovContoCorrente u = new MovContoCorrente().CreaUscita(importo.negate(), data, causale, tags, categoria);
            int maxId = 0;
            for(MovContoCorrente movimento : Movimenti ){
                if(movimento.Id > maxId)
                    maxId = movimento.Id;
            }
            u.Id = maxId+1;
            this.Movimenti.add(u);
            return u;
        }

        throw new IllegalArgumentException("L'importo deve essere maggiore di 0.");
    }

    /**
     * Ritorna tutti i movimenti tra due date
     * 
     * 
     * @param dataInizio
     *               la data iniziale della ricerca
     * @param dataFine
     *               la data finale della ricerca
     * 
     * @return
     * 				 una lista di MovContoCorrente con tutti i movimenti effettuati tra quelle date
     *         
     */
    public List<MovContoCorrente> GetMovimentiByDates(Date dataInizio, Date dataFine) {
        return  this.Movimenti.stream().filter(m -> m.getData().after(dataInizio) && m.getData().before(dataFine)).collect(Collectors.toList());
    }

    /**
     * Ritorna tutti i movimenti tra due date e con una specifica categoria
     * 
     * 
     * @param dataInizio
     *               la data iniziale della ricerca
     * @param dataFine
     *               la data finale della ricerca
     * @param codice
     *               Il codice della Categoria da filtrare
     * 
     * @return
     * 				 una lista di MovContoCorrente con tutti i movimenti effettuati tra quelle date e con quella categoria
     *         
     */
    public List<MovContoCorrente> GetMovimentiByDatesAndCategory(Date dataInizio, Date dataFine , String codice) {
    	List<MovContoCorrente> l = new ArrayList<MovContoCorrente>();
         return GetMovimentiByDates(dataInizio, dataFine).stream().filter(c -> c.getCategoria().Codice.equals(codice)).collect(Collectors.toList());
    }

    /**
     * Ritorna tutti i movimenti tra due date e con un tag specifico
     * 
     * 
     * @param dataInizio
     *               la data iniziale della ricerca
     * @param dataFine
     *               la data finale della ricerca
     * @param tag
     *               il tag da filtrare              
     * 
     * @return
     * 				 una lista di MovContoCorrente con tutti i movimenti effettuati tra quelle date e con quel tag
     *         
     */
    public List<MovContoCorrente> GetMovimentiByDatesAndTag(Date dataInizio, Date dataFine, String tag) {
        return GetMovimentiByDates(dataInizio, dataFine).stream().filter(t -> t.getTags().contains(tag)).collect(Collectors.toList());
    }

    /**
     * Ritorna il movimento con un Id specifico
     * 
     * 
     * @param id
     *               l'id da cercare              
     * 
     * @return MovBudget
     * 				 il movimento con quell'id
     *         
     */
    public MovContoCorrente GetMovimentoById(int id) {
    	return this.Movimenti.stream().filter(m-> m.Id == id).findFirst().orElse(null);
    }

    /**
     * Ritorna il saldo al momento attuale
     * 
     *                
     * 
     * @return BigDecimal
     * 				 il saldo attuale
     *         
     */
    public BigDecimal GetSaldoAttuale() {
        return GetSaldoByDate(Date.from(Instant.now()));
    }

    /**
     * Ritorna il saldo prima di una specifica data
     * 
     * 
     * @param data
     *               la data da confrontare              
     * 
     * @return BigDecimal
     * 				 il saldo rima della data passata
     *         
     */ 
    public BigDecimal GetSaldoByDate(Date data) {
        Optional<BigDecimal> ris = this.Movimenti.stream().filter(dt -> dt.getData().before(data)).map(x -> x.getImporto()).reduce(BigDecimal::add);
        return ris.orElse(null);
    }
    
    /**
     *Ritorna tutte i MovContoCorrente
     * 
     * @return
     * 				  ritorna tutte i MovContoCorrente
     * 
     */
	public List<MovContoCorrente> GetMovContoCorrente() {
		return Movimenti.stream().collect(Collectors.toList());
	}

	@Override
	public String toString() {
		return "ContoCorrente [Movimenti=" + Movimenti + "]";
	}
    
    

}
