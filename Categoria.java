package it.unicam.cs.pa.jbudget105788.project;


/**
 * Un oggetto della classe Categoria rappresenta una certa Categoria con il suo Codice, la sua Descrizione
 * e il suo Livello.
 * 
 * @author Paolo Campanelli 
 *         paolo.campanelli@studenti.unicam.it 105788
 *
 */
public class Categoria implements ICreaLivelloCategoria{
	
	//Il codice della Categoria
    public String Codice;
    
    //La descrizione della Categoria
    public String Descrizione;
    
    //Il livello della Categoria
    public LivelloEnum Livello;
    
    
    //il costruttore � protected per non far generare categorie 
    //al di fuori della struttura che la ospiter� in un applicazione futura
    public Categoria() {}

	/**
     * Crea una Categoria da inserire in una Struttura
     * 
     * @param codice
     *                Il codice da aggiungere
     * @param descrizione
     *                la descrizione da aggiungere    
     * @return Categoria
     * 				  ritorna la categoria 
     */
    public Categoria CreaLivello(String codice, String descrizione)
    {
        this.Codice = codice;
        this.Descrizione = descrizione;
        this.Livello = CalcolaLivelloDaCodice(codice);
        return this;
    }

    /**
     * Ritorna il padre di un codice passato ovvero se il codice è di
     * 9 cifre vengono passate le prime 6 mentre se è di 6 le prime 3 e se è di 3
     * viene passato null. 
     * Questo ci serve per sfruttare la gerarchia su un massimo di 3 livelli(che possono essere espansi su richiesta)
     * 
     * @param codice
     *                il codice da cui ritrovare il padre
     * @return String
     * 				  ritorna il padre del codice 
     * 
     * @throws IllegalArgumentException
     *                                  se il codice passato non è della lunghezza giusta
     */
    static public String RitornaPadre(String codice)
    {
        
        String s = new String();
    	if (codice.length() == 3) return null;
        if (codice.length() == 6) {
        	s =codice.substring(0, 3);
        	return s;
        }        	
        if (codice.length() == 9) {
        	s =codice.substring(0, 6);
        	return s;
        }
       
        	
        throw new IllegalArgumentException("il codice passato non è lungo ne 9 ne 6 ne 3 caratteri");
    }

    /**
     * Ritorna il Livello del codice passato a seconda della lunghezza del codice stesso
     * 
     * @param codice
     *                il codice da cui dedurre il livello
     * @return LivelloEnum
     * 				  ritorna il livello trovato 
     * 
     * @throws IllegalArgumentException
     *                                  se il codice passato non è della lunghezza giusta e non appartiene quindi a nessun livello
     */
    private static LivelloEnum CalcolaLivelloDaCodice(String codice)
    {
        switch (codice.length())
        {
            case 3:
                return LivelloEnum.root;
            case 6:
                return LivelloEnum.leg;
            case 9:
                return LivelloEnum.leaf;
            default:
                throw new IllegalArgumentException("il codice � sbagliato e non appartiene a nessun livello");
        }
    }
    
    

	@Override
	public String toString() {
		return "Categoria [Codice=" + Codice + ", Descrizione=" + Descrizione + ", Livello=" + Livello + "]";
	}
    
}



