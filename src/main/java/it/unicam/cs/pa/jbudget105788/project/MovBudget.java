package it.unicam.cs.pa.jbudget105788.project;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Un oggetto della classe MovBudget rappresenta un movimento della classe Budget 
 * 
 * @author Paolo Campanelli 
 *         paolo.campanelli@studenti.unicam.it 105788
 *
 */
public class MovBudget implements ICreaMovimento<MovBudget>{

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
    
    //il costruttore è protected per non far generare MovBudget
    //al di fuori del Budget che lo ospiterà in un applicazione futura
    protected MovBudget() {}
    

    /**
     *Crea un MovBudget positivo(un'entrata) che verrà associato ad un Budget.
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
    public MovBudget CreaEntrata(BigDecimal importo, Date data, String causale, List<String> tags, Categoria categoria)
    {
            this.ClassificazioneMovimento = ClassificazioneMovimentoEnum.E;
            this.setImporto(importo);
            this.setData(data);
            this.setCausale(causale);
            this.setTags(tags);
            this.setCategoria(categoria);
            return this;
    }

	/**
     * Crea un MovBudget negativo(un'uscita) che verrà associato ad un Budget.
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
    public MovBudget CreaUscita(BigDecimal importo, Date data, String causale, List<String> tags, Categoria categoria){


        this.ClassificazioneMovimento = ClassificazioneMovimentoEnum.U;
        this.setImporto(importo);
        this.setData(data);
        this.setCausale(causale);
        this.setTags(tags);
        this.setCategoria(categoria);
        return this;
    }


	@Override
	public String toString() {
		return "MovBudget [ClassificazioneMovimento=" + ClassificazioneMovimento + ", Importo=" + getImporto() + ", Data="
				+ getData() + ", Categoria=" + getCategoria() + ", Causale=" + getCausale() + ", Tags=" + getTags() + ", Id=" + Id + "]";
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
