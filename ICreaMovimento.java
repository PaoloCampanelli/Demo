package it.unicam.cs.pa.jbudget105788.project;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Una classe che implementa questa interfaccia deve avere i metodi per creare 
 * i movimenti sia che siano in entrata che in uscita
 * 
 * @author Paolo Campanelli 
 *         paolo.campanelli@studenti.unicam.it 105788
 *
 */
public interface ICreaMovimento<T> {

    T CreaEntrata(BigDecimal importo, Date data, String causale, List<String> tags, Categoria categoria);
    T CreaUscita(BigDecimal importo, Date data, String causale, List<String> tags, Categoria categoria);
}
