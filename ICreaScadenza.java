package it.unicam.cs.pa.jbudget105788.project;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Una classe che implementa questa interfaccia deve avere i metodi per creare le scadenze e poterle impostare pagate o no
 * 
 * @author Paolo Campanelli 
 *         paolo.campanelli@studenti.unicam.it 105788
 *
 */
public interface ICreaScadenza {


    Scadenza CreaScadenza(BigDecimal importoCapitale, BigDecimal importoInteressi, Date data, String descrizione, List<String> tags);
    
    void  SetPagata(ContoCorrente cc);
}
