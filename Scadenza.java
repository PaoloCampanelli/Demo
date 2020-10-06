package it.unicam.cs.pa.jbudget105788.project;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 * Un oggetto della classe Scadenza rappresenta una scadenza futura della 
 * classe Scadenzario che ha la possibilit� di sottrarsi autonomamente dal ContoCorrente se l'importo � sufficente
 * 
 * @author Paolo Campanelli 
 *         paolo.campanelli@studenti.unicam.it 105788
 *
 */
public class Scadenza implements ICreaScadenza{

	//L'id della scadenza
    public int Id;
    
    //L'importo capitale della scadenza
    protected BigDecimal ImportoCapitale;
    
    //L'importo degli interessi della scadenza
    private BigDecimal ImportoInteressi;
    
    //La data della scadenza
    private Date Data;
    
    //La descrizione della scadenza
    private String Descrizione;
    
    //Il flag che identifica se la scadenza � stata pagata o no
    private Boolean IsPagata;
    
    //La lista di tag che identifica la scadenza
    private List<String> Tags;
    
    /**
     *Crea una scadenza di un prestito, separando la quota capitale da quella interessi
     *e che verr� associata ad uno Scadenzario
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
     * @return this
     * 				  ritorna questa scadenza
     * 
     */
    public Scadenza CreaScadenza(BigDecimal importoCapitale, BigDecimal importoInteressi, Date data, String descrizione,List<String> tags)
    {
        this.setImportoCapitale(importoCapitale);
        this.setImportoInteressi(importoInteressi);
        this.setData(data);
        this.setDescrizione(descrizione);
        this.setTags(tags);
        this.setIsPagata(false);
        return this;
    }

    /**
     * Crea una scadenza ordinaria che verr� associata ad uno Scadenzario
     * 
     * @param importo
     *                l'importo da aggiungere
     * @param data
     *                la data da aggiungere 
     * @param descrizione
     *                la descrizione da aggiungere   
     * @param tags
     *                la lista di tag da aggiungere          
     * @return this
     * 				  ritorna questa scadenza
     * 
     */
    public Scadenza CreaScadenza(BigDecimal importo, Date data, String descrizione, List<String>tags)
    {
        this.setImportoCapitale(importo);
        this.setImportoInteressi(BigDecimal.ZERO);
        this.setData(data);
        this.setDescrizione(descrizione);
        this.setTags(tags);
        this.setIsPagata(false);
        return this;
    }

    /**
     * Ritorna l'importo di questa scadenza
     *           
     * @return l'importo sommato
     * 				  ritorna la somma dell'importo effettivo e degli interessi
     * 
     */
    public BigDecimal getImporto() {
        return getImportoCapitale().add(getImportoInteressi());
    }

    /**
     * Controlla se questa scadenza ha raggiunto la data per essere pagata e se cos� � 
     * crea un uscita sul conto con l'importo della scadenza piu l'eventuale interesse.
     * Questa operazione pu� essere fatta solo se nel conto ci sono abbastanza soldi per pagarla
     * se l'operazione va a buon fine il flag IsPagata diventa positivo
     * 
     * @param cc
     *                Il conto da cui scalare la scadenza        
     * 
     */
    public  void  SetPagata(ContoCorrente cc){
    		BigDecimal tot = getImporto();
    		if(getData().before(Date.from(Instant.now()))) {
    			if(cc.GetSaldoAttuale().compareTo(tot)>=0) {
    				if(getIsPagata()==false) {
    					setIsPagata(true);
    					cc.CreaUscita(tot, getData(), getDescrizione(), getTags(), cc.GetMovContoCorrente().get(0).getCategoria());
    				}	
    			}
    		}
    	}

	@Override
	public String toString() {
		return "Scadenza [Id=" + Id + ", ImportoCapitale=" + getImportoCapitale() + ", ImportoInteressi=" + getImportoInteressi()
				+ ", Data=" + getData() + ", Descrizione=" + getDescrizione() + ", IsPagata=" + getIsPagata() + ", Tags=" + getTags() + "]";
	}

	/**
	 * @return the importoCapitale
	 */
	public BigDecimal getImportoCapitale() {
		return ImportoCapitale;
	}

	/**
	 * @param importoCapitale the importoCapitale to set
	 */
	public void setImportoCapitale(BigDecimal importoCapitale) {
		ImportoCapitale = importoCapitale;
	}

	/**
	 * @return the data
	 */
	public Date getData() {
		return Data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Date data) {
		Data = data;
	}

	/**
	 * @return the importoInteressi
	 */
	public BigDecimal getImportoInteressi() {
		return ImportoInteressi;
	}

	/**
	 * @param importoInteressi the importoInteressi to set
	 */
	public void setImportoInteressi(BigDecimal importoInteressi) {
		ImportoInteressi = importoInteressi;
	}

	/**
	 * @return the descrizione
	 */
	public String getDescrizione() {
		return Descrizione;
	}

	/**
	 * @param descrizione the descrizione to set
	 */
	public void setDescrizione(String descrizione) {
		Descrizione = descrizione;
	}

	/**
	 * @return the tags
	 */
	public List<String> getTags() {
		return Tags;
	}

	/**
	 * @param tags the tags to set
	 */
	public void setTags(List<String> tags) {
		Tags = tags;
	}

	/**
	 * @return the isPagata
	 */
	public Boolean getIsPagata() {
		return IsPagata;
	}

	/**
	 * @param isPagata the isPagata to set
	 */
	public void setIsPagata(Boolean isPagata) {
		IsPagata = isPagata;
	}
    
    
    			
    	
    }

