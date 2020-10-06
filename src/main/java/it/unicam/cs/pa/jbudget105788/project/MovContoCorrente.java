package it.unicam.cs.pa.jbudget105788.project;
import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Un oggetto della classe MovContoCorrente rappresenta un movimento della classe ContoCorrente 
 * 
 * @author Paolo Campanelli 
 *         paolo.campanelli@studenti.unicam.it 105788
 *
 */
public class MovContoCorrente implements ICreaMovimento<MovContoCorrente>{

	//la classificazione del movimento
    protected ClassificazioneMovimentoEnum ClassificazioneMovimento;
    
    //L'importo del movimento
    private BigDecimal Importo;
    
    //La data del movimento
    private Date Data;
    
    //La categoria del movimento
    private Categoria Categoria;
    
    //La causale del movimento 
    private String Causale;
    
    //La lista dei tag del movimento
    private List<String> Tags;
    
    //L'id del movimento
    public int Id;
    
    //il costruttore è protected per non far generare MovContoCorrente
    //al di fuori del ContoCorrente che lo ospiterà in un applicazione futura
    protected MovContoCorrente() {}

	/**
     *Crea un MovContoCorrente positivo(un'entrata) che verrà associato ad un ContoCorrente.
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
     * @return this
     * 				  ritorna questo movimento 
     * 
     */
    public MovContoCorrente CreaEntrata(BigDecimal importo, Date data, String causale, List<String> tags, Categoria categoria) {
        this.ClassificazioneMovimento = ClassificazioneMovimentoEnum.E;
        this.Importo = importo;
        this.Data = data;
        this.Causale = causale;
        this.Tags = tags;
        this.Categoria = categoria;
        return this;
    }

    /**
     * Crea un MovContoCorrente negativo(un'uscita) che verr� associato ad un ContoCorrente.
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
     * @return this
     * 				  ritorna questo movimento 
     * 
     */
    public MovContoCorrente CreaUscita(BigDecimal importo, Date data, String causale, List<String> tags, Categoria categoria) {
        this.ClassificazioneMovimento = ClassificazioneMovimentoEnum.U;
        this.Importo = importo;
        this.Data = data;
        this.Causale = causale;
        this.Tags = tags;
        this.Categoria = categoria;
        return this;
    }

	@Override
	public String toString() {
		return "MovContoCorrente [ClassificazioneMovimento=" + ClassificazioneMovimento + ", Importo=" + Importo
				+ ", Data=" + Data + ", Categoria=" + Categoria + ", Causale=" + Causale + ", Tags=" + Tags + ", Id="
				+ Id + "]";
	}


    /**
     * @return the importo
     */
    public BigDecimal getImporto() {
        return Importo;
    }


    /**
     * @param importo the importo to set
     */
    public void setImporto(BigDecimal importo) {
        Importo = importo;
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
     * @return the causale
     */
    public String getCausale() {
        return Causale;
    }


    /**
     * @param causale the causale to set
     */
    public void setCausale(String causale) {
        Causale = causale;
    }


    /**
     * @return the categoria
     */
    public Categoria getCategoria() {
        return Categoria;
    }


    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(Categoria categoria) {
        Categoria = categoria;
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


}
